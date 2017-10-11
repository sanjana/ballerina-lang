/*
 * Copyright (c) 2017, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballerinalang.composer.service.workspace.util;

import org.ballerinalang.compiler.CompilerPhase;
import org.ballerinalang.composer.service.workspace.langserver.model.Action;
import org.ballerinalang.composer.service.workspace.langserver.model.AnnotationAttachment;
import org.ballerinalang.composer.service.workspace.langserver.model.AnnotationDef;
import org.ballerinalang.composer.service.workspace.langserver.model.Connector;
import org.ballerinalang.composer.service.workspace.langserver.model.Function;
import org.ballerinalang.composer.service.workspace.langserver.model.ModelPackage;
import org.ballerinalang.composer.service.workspace.langserver.model.Parameter;
import org.ballerinalang.composer.service.workspace.langserver.model.Struct;
import org.ballerinalang.composer.service.workspace.langserver.model.StructField;
import org.ballerinalang.composer.service.workspace.rest.datamodel.BallerinaFile;
import org.ballerinalang.composer.service.workspace.rest.datamodel.ComposerDiagnosticListener;
import org.ballerinalang.composer.service.workspace.rest.datamodel.InMemoryPackageRepository;
import org.ballerinalang.model.elements.PackageID;
import org.ballerinalang.repository.PackageRepository;
import org.ballerinalang.util.diagnostic.Diagnostic;
import org.ballerinalang.util.diagnostic.DiagnosticListener;
import org.wso2.ballerinalang.compiler.Compiler;
import org.wso2.ballerinalang.compiler.PackageLoader;
import org.wso2.ballerinalang.compiler.tree.BLangAction;
import org.wso2.ballerinalang.compiler.tree.BLangAnnotation;
import org.wso2.ballerinalang.compiler.tree.BLangAnnotationAttachment;
import org.wso2.ballerinalang.compiler.tree.BLangConnector;
import org.wso2.ballerinalang.compiler.tree.BLangFunction;
import org.wso2.ballerinalang.compiler.tree.BLangIdentifier;
import org.wso2.ballerinalang.compiler.tree.BLangPackage;
import org.wso2.ballerinalang.compiler.tree.BLangStruct;
import org.wso2.ballerinalang.compiler.tree.BLangVariable;
import org.wso2.ballerinalang.compiler.util.CompilerContext;
import org.wso2.ballerinalang.compiler.util.CompilerOptions;
import org.wso2.ballerinalang.compiler.util.Name;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ballerinalang.compiler.CompilerOptionName.COMPILER_PHASE;
import static org.ballerinalang.compiler.CompilerOptionName.PRESERVE_WHITESPACE;
import static org.ballerinalang.compiler.CompilerOptionName.SOURCE_ROOT;

/**
 * Utility methods for workspace service
 */
public class WorkspaceUtils {

    /**
     * This method is designed to generate the Ballerina model and Diagnostic information for a given Ballerina file
     * saved in the file-system.
     * @param filePath - Path to Ballerina file.
     * @param fileName - File name. This can be any arbitrary name as as we haven't save the file yet.
     * @return BallerinaFile - Object which contains Ballerina model and Diagnostic information
     */
    public static BallerinaFile getBallerinaFile(String filePath, String fileName) {
        CompilerContext context = new CompilerContext();
        CompilerOptions options = CompilerOptions.getInstance(context);
        options.put(SOURCE_ROOT, filePath);
        options.put(COMPILER_PHASE, CompilerPhase.CODE_ANALYZE.toString());
        options.put(PRESERVE_WHITESPACE, Boolean.TRUE.toString());
        return getBallerinaFile(fileName, context);
    }

    /**
     * This method is designed to generate the Ballerina model and Diagnostic information for a given Ballerina content.
     * Ideal use case is generating Ballerina model and Diagnostic information for unsaved Ballerina files.
     *
     * @param fileName - File name. This can be any arbitrary name as as we haven't save the file yet.
     * @param source - Ballerina source content that needs to be parsed.
     * @param compilerPhase - This will tell up to which point(compiler phase) we should process the model
     * @return BallerinaFile - Object which contains Ballerina model and Diagnostic information
     */
    public static BallerinaFile getBallerinaFileForContent(String fileName, String source,
                                                           CompilerPhase compilerPhase) {
        CompilerContext context = prepareCompilerContext(fileName, source);
        CompilerOptions options = CompilerOptions.getInstance(context);
        options.put(COMPILER_PHASE, compilerPhase.toString());
        options.put(PRESERVE_WHITESPACE, Boolean.TRUE.toString());

        return getBallerinaFile(fileName, context);
    }

    /**
     * Returns a CompilerContext for the provided fileName and Ballerina source content.
     *
     * @param fileName - File name. This can be any arbitrary name as as we haven't save the file yet.
     * @param source - Ballerina source content that needs to be parsed.
     * @return CompilerContext
     */
    private static CompilerContext prepareCompilerContext(String fileName, String source) {
        CompilerContext context = new CompilerContext();
        List<Name> names = new ArrayList<>();
        names.add(new org.wso2.ballerinalang.compiler.util.Name("."));
        // Registering custom PackageRepository to provide ballerina content without a file in file-system
        context.put(PackageRepository.class, new InMemoryPackageRepository(
                new PackageID(names, new org.wso2.ballerinalang.compiler.util.Name("0.0.0")),
                "", fileName, source.getBytes(StandardCharsets.UTF_8)));
        return context;
    }

    /**
     * Returns an object which contains Ballerina model and Diagnostic information
     * @param fileName - File name
     * @param context - CompilerContext
     * @return BallerinaFile - Object which contains Ballerina model and Diagnostic information
     */
    private static BallerinaFile getBallerinaFile(String fileName, CompilerContext context) {
        List<Diagnostic> diagnostics = new ArrayList<>();
        ComposerDiagnosticListener composerDiagnosticListener = new ComposerDiagnosticListener(diagnostics);
        context.put(DiagnosticListener.class, composerDiagnosticListener);
        Compiler compiler = Compiler.getInstance(context);

        BallerinaFile ballerinaFile = new BallerinaFile();
        compiler.compile(fileName);
        ballerinaFile.setBLangPackage((BLangPackage) compiler.getAST());
        ballerinaFile.setDiagnostics(diagnostics);
        return ballerinaFile;
    }

    /**
     * Get All Native Packages
     *
     * @return {@link Map} Package name, package functions and connectors
     */
    public static Map<String, ModelPackage> getAllPackages() {
        final Map<String, ModelPackage> modelPackage = new HashMap<>();

        CompilerContext context = prepareCompilerContext("", "");
        PackageLoader packageLoader = PackageLoader.getInstance(context);
        // max depth for the recursive function which search for child directories
        int maxDepth = 15;
        Set<PackageID> packages = packageLoader.listPackages(maxDepth);
        packages.stream().forEach(pkg -> {
            Name version = pkg.getPackageVersion();
            BLangIdentifier bLangIdentifier = new BLangIdentifier();
            bLangIdentifier.setValue(version.getValue());

            List<BLangIdentifier> pkgNameComps = pkg.getNameComps().stream().map(nameToBLangIdentifier)
                    .collect(Collectors.<BLangIdentifier>toList());

            //TODO Remove this if check once other packages are available to load.
            if (!"[ballerina, lang, btype]".equals(pkgNameComps.toString())
                    && !"[ballerina, mock]".equals(pkgNameComps.toString())
                    && !"[ballerina, test]".equals(pkgNameComps.toString())
                    && !"[ballerina, lang, messages]".equals(pkgNameComps.toString())
                    && !"[ballerina, net, jms]".equals(pkgNameComps.toString())
                    && !pkgNameComps.toString().contains("[org, wso2, ballerina, connectors")) {
                org.wso2.ballerinalang.compiler.tree.BLangPackage bLangPackage = packageLoader
                        .loadPackage(pkgNameComps, bLangIdentifier);
                loadPackageMap(pkg.getName().getValue(), bLangPackage, modelPackage);
            }
        });
        return modelPackage;
    }

    /**
     * Function to convert org.wso2.ballerinalang.compiler.util.Name instance to
     * org.wso2.ballerinalang.compiler.tree.BLangIdentifier instance.
     */
    static java.util.function.Function<Name, BLangIdentifier> nameToBLangIdentifier =
            new java.util.function.Function<Name, BLangIdentifier>() {

                public BLangIdentifier apply(Name name) {
                    BLangIdentifier bLangIdentifier = new BLangIdentifier();
                    bLangIdentifier.setValue(name.getValue());
                    return bLangIdentifier;
                }
            };

    /**
     * Function to convert org.wso2.ballerinalang.compiler.tree.BLangIdentifier instance to
     * java.lang.String instance.
     */
    public static java.util.function.Function<BLangIdentifier, String> bLangIdentifierToString =
            new java.util.function.Function<BLangIdentifier, String>() {
                public String apply(BLangIdentifier name) {
                    return name.getValue();
                }
            };

    /**
     * Add connectors, functions, annotations etc. to packages.
     * @param packageName package name
     * @param pkg BLangPackage instance
     * @param packages packages map
     */
    public static void loadPackageMap(String packageName, final org.wso2.ballerinalang.compiler.tree.BLangPackage pkg,
                                       Map<String, ModelPackage> packages) {
        if (pkg != null) {
            pkg.getFunctions().forEach((function) -> extractFunction(packages, packageName, function));
            pkg.getStructs().forEach((struct) -> extractStruct(packages, packageName, struct));
            pkg.getAnnotations().forEach((annotation) -> extractAnnotation(packages, packageName, annotation));
            pkg.getConnectors().forEach((connector) -> extractConnector(packages, packageName, connector));
        }
    }

    /**
     * Extract annotations from ballerina lang
     * @param packages packages to send
     * @param annotation annotation
     * */
    private static void extractAnnotation(Map<String, ModelPackage> packages, String packagePath,
                                          BLangAnnotation annotation) {
        if (packages.containsKey(packagePath)) {
            ModelPackage modelPackage = packages.get(packagePath);
        
            modelPackage.addAnnotationsItem(AnnotationDef.convertToPackageModel(annotation));
        } else {
            ModelPackage modelPackage = new ModelPackage();
            modelPackage.setName(packagePath);
        
            modelPackage.addAnnotationsItem(AnnotationDef.convertToPackageModel(annotation));
            packages.put(packagePath, modelPackage);
        }
    }

    /**
     * Extract connectors from ballerina lang
     * @param packages packages to send
     * @param connector connector
     * */
    private static void extractConnector(Map<String, ModelPackage> packages, String packagePath,
                                  BLangConnector connector) {
        if (packages.containsKey(packagePath)) {
            ModelPackage modelPackage = packages.get(packagePath);
            List<Parameter> parameters = new ArrayList<>();
            addParameters(parameters, connector.getParameters());

            List<AnnotationAttachment> annotations = new ArrayList<>();
            addAnnotations(annotations, connector.getAnnotationAttachments());

            List<Action> actions = new ArrayList<>();
            addActions(actions, connector.getActions());

            modelPackage.addConnectorsItem(createNewConnector(connector.getName().getValue(),
                    annotations, actions, parameters, null));
        } else {
            ModelPackage modelPackage = new ModelPackage();
            modelPackage.setName(packagePath);

            List<Parameter> parameters = new ArrayList<>();
            addParameters(parameters, connector.getParameters());

            List<AnnotationAttachment> annotations = new ArrayList<>();
            addAnnotations(annotations, connector.getAnnotationAttachments());

            List<Action> actions = new ArrayList<>();
            addActions(actions, connector.getActions());

            modelPackage.addConnectorsItem(createNewConnector(connector.getName().getValue(),
                    annotations, actions, parameters, null));
            packages.put(packagePath, modelPackage);
        }
    }

    /**
     * Extract Functions from ballerina lang.
     * @param packages packages to send.
     * @param packagePath package path
     * @param function function.
     * */
    private static void extractFunction(Map<String, ModelPackage> packages, String packagePath,
                                        BLangFunction function) {
        if (packages.containsKey(packagePath)) {
            ModelPackage modelPackage = packages.get(packagePath);
            List<Parameter> parameters = new ArrayList<>();
            addParameters(parameters, function.getParameters());

            List<Parameter> returnParameters = new ArrayList<>();
            addParameters(returnParameters, function.getReturnParameters());

            List<AnnotationAttachment> annotations = new ArrayList<>();
            addAnnotations(annotations, function.getAnnotationAttachments());

            modelPackage.addFunctionsItem(createNewFunction(function.getName().getValue(),
                    annotations, parameters, returnParameters));
        } else {
            ModelPackage modelPackage = new ModelPackage();
            modelPackage.setName(packagePath);
            List<Parameter> parameters = new ArrayList<>();
            addParameters(parameters, function.getParameters());

            List<Parameter> returnParameters = new ArrayList<>();
            addParameters(returnParameters, function.getReturnParameters());

            List<AnnotationAttachment> annotations = new ArrayList<>();
            addAnnotations(annotations, function.getAnnotationAttachments());

            modelPackage.addFunctionsItem(createNewFunction(function.getName().getValue(),
                    annotations, parameters, returnParameters));
            packages.put(packagePath, modelPackage);
        }
    }

    /**
     * Extract Structs from ballerina lang.
     * @param packages packages to send.
     * @param packagePath packagePath.
     * @param struct struct.
     * */
    private static void extractStruct(Map<String, ModelPackage> packages, String packagePath,
                                          BLangStruct struct) {
        if (packages.containsKey(packagePath)) {
            ModelPackage modelPackage = packages.get(packagePath);
            modelPackage.addStructsItem(createNewStruct(struct.getName().getValue(), struct.getFields()));
        } else {
            ModelPackage modelPackage = new ModelPackage();
            modelPackage.setName(packagePath);
            modelPackage.addStructsItem(createNewStruct(struct.getName().getValue(), struct.getFields()));
            packages.put(packagePath, modelPackage);
        }
    }

    /**
     * Add parameters to a list from ballerina lang param list.
     * @param params params to send.
     * @param argumentTypeNames argument types
     * */
    private static void addParameters(List<Parameter> params, List<BLangVariable> argumentTypeNames) {
        if (argumentTypeNames != null) {
            argumentTypeNames.forEach(item -> params.add(createNewParameter(item.getName().getValue(),
                    item.getTypeNode().type.toString())));
        }
    }

    /**
     * Add annotations to a list from ballerina lang annotation list
     * @param annotations annotations list to be sent
     * @param bLangAnnotationAttachment annotations
     * */
    private static void addAnnotations(List<AnnotationAttachment> annotations,
                                       List<BLangAnnotationAttachment> bLangAnnotationAttachment) {
        bLangAnnotationAttachment.forEach(annotation -> annotations.add(AnnotationAttachment.
                convertToPackageModel(annotation)));
    }

    /**
     * Add Actions to the connector.
     * @param actionsList action list to be sent
     * @param actions native actions retrieve from the connector
     * */
    private static void addActions(List<Action> actionsList, List<BLangAction> actions) {
        actions.forEach(action -> actionsList.add(extractAction(action)));
    }

    /**
     * Extract action details from a connector.
     * @param action action.
     * @return {Action} action
     * */
    private static Action extractAction(BLangAction action) {
        List<Parameter> parameters = new ArrayList<>();
        addParameters(parameters, action.getParameters());

        List<AnnotationAttachment> annotations = new ArrayList<>();
        addAnnotations(annotations, action.getAnnotationAttachments());

        List<Parameter> returnParameters = new ArrayList<>();
        addParameters(returnParameters, action.getReturnParameters());
        return createNewAction(action.getName().getValue(), parameters, returnParameters, annotations);
    }

    /**
     * Create new action
     * @param name action name
     * @param params list of params
     * @param returnParams list of return params
     * @param annotations list of annotations
     * @return {Action} action
     * */
    private static Action createNewAction(String name, List<Parameter> params, List<Parameter> returnParams,
                                   List<AnnotationAttachment> annotations) {
        Action action = new Action();
        action.setName(name);
        action.setParameters(params);
        action.setReturnParams(returnParams);
        action.setAnnotations(annotations);
        return action;
    }

    /**
     * Create new parameter
     * @param name parameter name
     * @param type parameter type
     * @return {Parameter} parameter
     * */
    private static Parameter createNewParameter(String name, String type) {
        Parameter parameter = new Parameter();
        parameter.setType(type);
        parameter.setName(name);
        return parameter;
    }

    /**
     * Create new function
     * @param name name of the function
     * @param annotations list of annotations
     * @param params list of parameters
     * @param returnParams list of return params
     * @return {Function} function
     * */
    private static Function createNewFunction(String name, List<AnnotationAttachment> annotations,
                                              List<Parameter> params, List<Parameter> returnParams) {
        Function function = new Function();
        function.setName(name);
        function.setAnnotations(annotations);
        function.setParameters(params);
        function.setReturnParams(returnParams);
        return function;
    }

    /**
     * Create new struct
     * @param name name of the struct
     * @param fields    field definiton statements
     * @return {Function} function
     * */
    private static Struct createNewStruct(String name, List<BLangVariable> fields) {
        Struct struct = new Struct(name);
        fields.forEach((field) -> {
            StructField structField = createNewStructField(field.getName().getValue(),
                    field.getTypeNode().type.toString());
            struct.addStructField(structField);
        });
        return struct;
    }

    /**
     * create a new struct field
     * @param name name of the field
     * @param type type of the field
     * @return
     */
    private static StructField createNewStructField(String name, String type) {
        StructField structField = new StructField(name, type);
        return structField;
    }

    /**
     * Create new connector
     * @param name name of the connector
     * @param annotations list of annotation
     * @param actions list of actions
     * @param params list of params
     * @param returnParams list of return params
     * @return {Connector} connector
     * */
    private static Connector createNewConnector(String name, List<AnnotationAttachment> annotations,
                                                List<Action> actions, List<Parameter> params,
                                                List<Parameter> returnParams) {
        Connector connector = new Connector();
        connector.setName(name);
        connector.setActions(actions);
        connector.setParameters(params);
        connector.setAnnotations(annotations);
        connector.setReturnParameters(returnParams);
        return connector;
    }
}
