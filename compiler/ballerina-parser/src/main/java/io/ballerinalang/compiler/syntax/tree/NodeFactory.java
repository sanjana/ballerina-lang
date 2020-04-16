/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerinalang.compiler.syntax.tree;

import io.ballerinalang.compiler.internal.parser.tree.STNode;
import io.ballerinalang.compiler.internal.parser.tree.STNodeFactory;
import java.util.Objects;

/**
 * A factory for creating nodes in the syntax tree.
 *
 * This is a generated class.
 *
 * @since 1.3.0
 */
public abstract class NodeFactory extends AbstractNodeFactory {

    private NodeFactory() {
    }

    public static ModulePartNode createModulePart(
            NodeList<ImportDeclarationNode> imports,
            NodeList<ModuleMemberDeclarationNode> members,
            Token eofToken) {
        Objects.requireNonNull(imports, "imports must not be null");
        Objects.requireNonNull(members, "members must not be null");
        Objects.requireNonNull(eofToken, "eofToken must not be null");

        STNode stModulePart = STNodeFactory.createModulePartNode(
                imports.underlyingListNode().internalNode(),
                members.underlyingListNode().internalNode(),
                eofToken.internalNode());
        return stModulePart.createUnlinkedFacade();
    }

    public static FunctionDefinitionNode createFunctionDefinition(
            MetadataNode metadata,
            Token visibilityQualifier,
            Token functionKeyword,
            IdentifierToken functionName,
            Token openParenToken,
            NodeList<ParameterNode> parameters,
            Token closeParenToken,
            Node returnTypeDesc,
            BlockStatementNode functionBody) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(functionKeyword, "functionKeyword must not be null");
        Objects.requireNonNull(functionName, "functionName must not be null");
        Objects.requireNonNull(openParenToken, "openParenToken must not be null");
        Objects.requireNonNull(parameters, "parameters must not be null");
        Objects.requireNonNull(closeParenToken, "closeParenToken must not be null");
        Objects.requireNonNull(returnTypeDesc, "returnTypeDesc must not be null");
        Objects.requireNonNull(functionBody, "functionBody must not be null");

        STNode stFunctionDefinition = STNodeFactory.createFunctionDefinitionNode(
                metadata.internalNode(),
                visibilityQualifier.internalNode(),
                functionKeyword.internalNode(),
                functionName.internalNode(),
                openParenToken.internalNode(),
                parameters.underlyingListNode().internalNode(),
                closeParenToken.internalNode(),
                returnTypeDesc.internalNode(),
                functionBody.internalNode());
        return stFunctionDefinition.createUnlinkedFacade();
    }

    public static ImportDeclarationNode createImportDeclaration(
            Token importKeyword,
            Token orgName,
            Node moduleName,
            Node version,
            Node prefix,
            Token semicolon) {
        Objects.requireNonNull(importKeyword, "importKeyword must not be null");
        Objects.requireNonNull(orgName, "orgName must not be null");
        Objects.requireNonNull(moduleName, "moduleName must not be null");
        Objects.requireNonNull(version, "version must not be null");
        Objects.requireNonNull(prefix, "prefix must not be null");
        Objects.requireNonNull(semicolon, "semicolon must not be null");

        STNode stImportDeclaration = STNodeFactory.createImportDeclarationNode(
                importKeyword.internalNode(),
                orgName.internalNode(),
                moduleName.internalNode(),
                version.internalNode(),
                prefix.internalNode(),
                semicolon.internalNode());
        return stImportDeclaration.createUnlinkedFacade();
    }

    public static ListenerDeclarationNode createListenerDeclaration(
            MetadataNode metadata,
            Token visibilityQualifier,
            Token listenerKeyword,
            Node typeDescriptor,
            Token variableName,
            Token equalsToken,
            Node initializer,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(listenerKeyword, "listenerKeyword must not be null");
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
        Objects.requireNonNull(variableName, "variableName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(initializer, "initializer must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stListenerDeclaration = STNodeFactory.createListenerDeclarationNode(
                metadata.internalNode(),
                visibilityQualifier.internalNode(),
                listenerKeyword.internalNode(),
                typeDescriptor.internalNode(),
                variableName.internalNode(),
                equalsToken.internalNode(),
                initializer.internalNode(),
                semicolonToken.internalNode());
        return stListenerDeclaration.createUnlinkedFacade();
    }

    public static TypeDefinitionNode createTypeDefinitionNode(
            MetadataNode metadata,
            Token visibilityQualifier,
            Token typeKeyword,
            Token typeName,
            Node typeDescriptor,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(typeKeyword, "typeKeyword must not be null");
        Objects.requireNonNull(typeName, "typeName must not be null");
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stTypeDefinitionNode = STNodeFactory.createTypeDefinitionNode(
                metadata.internalNode(),
                visibilityQualifier.internalNode(),
                typeKeyword.internalNode(),
                typeName.internalNode(),
                typeDescriptor.internalNode(),
                semicolonToken.internalNode());
        return stTypeDefinitionNode.createUnlinkedFacade();
    }

    public static ServiceDeclarationNode createServiceDeclaration(
            MetadataNode metadata,
            Token serviceKeyword,
            IdentifierToken serviceName,
            Token onKeyword,
            NodeList<ExpressionNode> expressions,
            Node serviceBody) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(serviceKeyword, "serviceKeyword must not be null");
        Objects.requireNonNull(serviceName, "serviceName must not be null");
        Objects.requireNonNull(onKeyword, "onKeyword must not be null");
        Objects.requireNonNull(expressions, "expressions must not be null");
        Objects.requireNonNull(serviceBody, "serviceBody must not be null");

        STNode stServiceDeclaration = STNodeFactory.createServiceDeclarationNode(
                metadata.internalNode(),
                serviceKeyword.internalNode(),
                serviceName.internalNode(),
                onKeyword.internalNode(),
                expressions.underlyingListNode().internalNode(),
                serviceBody.internalNode());
        return stServiceDeclaration.createUnlinkedFacade();
    }

    public static AssignmentStatementNode createAssignmentStatement(
            Node varRef,
            Token equalsToken,
            ExpressionNode expression,
            Token semicolonToken) {
        Objects.requireNonNull(varRef, "varRef must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stAssignmentStatement = STNodeFactory.createAssignmentStatementNode(
                varRef.internalNode(),
                equalsToken.internalNode(),
                expression.internalNode(),
                semicolonToken.internalNode());
        return stAssignmentStatement.createUnlinkedFacade();
    }

    public static CompoundAssignmentStatementNode createCompoundAssignmentStatement(
            ExpressionNode lhsExpression,
            Token binaryOperator,
            Token equalsToken,
            ExpressionNode rhsExpression,
            Token semicolonToken) {
        Objects.requireNonNull(lhsExpression, "lhsExpression must not be null");
        Objects.requireNonNull(binaryOperator, "binaryOperator must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(rhsExpression, "rhsExpression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stCompoundAssignmentStatement = STNodeFactory.createCompoundAssignmentStatementNode(
                lhsExpression.internalNode(),
                binaryOperator.internalNode(),
                equalsToken.internalNode(),
                rhsExpression.internalNode(),
                semicolonToken.internalNode());
        return stCompoundAssignmentStatement.createUnlinkedFacade();
    }

    public static LocalTypeDefinitionStatementNode createLocalTypeDefinitionStatement(
            Node annots,            
            Token typeKeyword,
            Node typeName,
            Node typeDescriptor,
            Token semicolonToken) {
        Objects.requireNonNull(annots, "typeKeyword must not be null");
        Objects.requireNonNull(typeKeyword, "typeKeyword must not be null");
        Objects.requireNonNull(typeName, "typeName must not be null");
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stLocalTypeDefinitionStatement = STNodeFactory.createLocalTypeDefinitionStatementNode(
                annots.internalNode(),
                typeKeyword.internalNode(),
                typeName.internalNode(),
                typeDescriptor.internalNode(),
                semicolonToken.internalNode());
        return stLocalTypeDefinitionStatement.createUnlinkedFacade();
    }

    public static VariableDeclarationNode createVariableDeclaration(
            NodeList<AnnotationNode> annotations,
            Token finalKeyword,
            Node typeName,
            Token variableName,
            Token equalsToken,
            ExpressionNode initializer,
            Token semicolonToken) {
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(finalKeyword, "finalKeyword must not be null");
        Objects.requireNonNull(typeName, "typeName must not be null");
        Objects.requireNonNull(variableName, "variableName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(initializer, "initializer must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stVariableDeclaration = STNodeFactory.createVariableDeclarationNode(
                annotations.underlyingListNode().internalNode(),
                finalKeyword.internalNode(),
                typeName.internalNode(),
                variableName.internalNode(),
                equalsToken.internalNode(),
                initializer.internalNode(),
                semicolonToken.internalNode());
        return stVariableDeclaration.createUnlinkedFacade();
    }

    public static BlockStatementNode createBlockStatement(
            Token openBraceToken,
            NodeList<StatementNode> statements,
            Token closeBraceToken) {
        Objects.requireNonNull(openBraceToken, "openBraceToken must not be null");
        Objects.requireNonNull(statements, "statements must not be null");
        Objects.requireNonNull(closeBraceToken, "closeBraceToken must not be null");

        STNode stBlockStatement = STNodeFactory.createBlockStatementNode(
                openBraceToken.internalNode(),
                statements.underlyingListNode().internalNode(),
                closeBraceToken.internalNode());
        return stBlockStatement.createUnlinkedFacade();
    }

    public static BreakStatementNode createBreakStatement(
            Token breakToken,
            Token semicolonToken) {
        Objects.requireNonNull(breakToken, "breakToken must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stBreakStatement = STNodeFactory.createBreakStatementNode(
                breakToken.internalNode(),
                semicolonToken.internalNode());
        return stBreakStatement.createUnlinkedFacade();
    }

    public static CallStatementNode createCallStatement(
            Node expression,
            Token semicolonToken) {
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stCallStatement = STNodeFactory.createCallStatementNode(
                expression.internalNode(),
                semicolonToken.internalNode());
        return stCallStatement.createUnlinkedFacade();
    }

    public static ContinueStatementNode createContinueStatement(
            Token continueToken,
            Token semicolonToken) {
        Objects.requireNonNull(continueToken, "continueToken must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stContinueStatement = STNodeFactory.createContinueStatementNode(
                continueToken.internalNode(),
                semicolonToken.internalNode());
        return stContinueStatement.createUnlinkedFacade();
    }

    public static ExternalFunctionBodyNode createExternalFunctionBody(
            Token equalsToken,
            NodeList<AnnotationNode> annotations,
            Token externalKeyword,
            Token semicolonToken) {
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(externalKeyword, "externalKeyword must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stExternalFunctionBody = STNodeFactory.createExternalFunctionBodyNode(
                equalsToken.internalNode(),
                annotations.underlyingListNode().internalNode(),
                externalKeyword.internalNode(),
                semicolonToken.internalNode());
        return stExternalFunctionBody.createUnlinkedFacade();
    }

    public static IfElseStatementNode createIfElseStatement(
            Token ifKeyword,
            Node condition,
            Node ifBody,
            Node elseBody) {
        Objects.requireNonNull(ifKeyword, "ifKeyword must not be null");
        Objects.requireNonNull(condition, "condition must not be null");
        Objects.requireNonNull(ifBody, "ifBody must not be null");
        Objects.requireNonNull(elseBody, "elseBody must not be null");

        STNode stIfElseStatement = STNodeFactory.createIfElseStatementNode(
                ifKeyword.internalNode(),
                condition.internalNode(),
                ifBody.internalNode(),
                elseBody.internalNode());
        return stIfElseStatement.createUnlinkedFacade();
    }

    public static ElseBlockNode createElseBlock(
            Token elseKeyword,
            Node elseBody) {
        Objects.requireNonNull(elseKeyword, "elseKeyword must not be null");
        Objects.requireNonNull(elseBody, "elseBody must not be null");

        STNode stElseBlock = STNodeFactory.createElseBlockNode(
                elseKeyword.internalNode(),
                elseBody.internalNode());
        return stElseBlock.createUnlinkedFacade();
    }

    public static WhileStatementNode createWhileStatement(
            Token whileKeyword,
            ExpressionNode condition,
            Node whileBody) {
        Objects.requireNonNull(whileKeyword, "whileKeyword must not be null");
        Objects.requireNonNull(condition, "condition must not be null");
        Objects.requireNonNull(whileBody, "whileBody must not be null");

        STNode stWhileStatement = STNodeFactory.createWhileStatementNode(
                whileKeyword.internalNode(),
                condition.internalNode(),
                whileBody.internalNode());
        return stWhileStatement.createUnlinkedFacade();
    }

    public static PanicStatementNode createPanicStatement(
            Token panicKeyword,
            ExpressionNode expression,
            Token semicolonToken) {
        Objects.requireNonNull(panicKeyword, "panicKeyword must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stPanicStatement = STNodeFactory.createPanicStatementNode(
                panicKeyword.internalNode(),
                expression.internalNode(),
                semicolonToken.internalNode());
        return stPanicStatement.createUnlinkedFacade();
    }

    public static ReturnStatementNode createReturnStatement(
            Token returnKeyword,
            ExpressionNode expression,
            Token semicolonToken) {
        Objects.requireNonNull(returnKeyword, "returnKeyword must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stReturnStatement = STNodeFactory.createReturnStatementNode(
                returnKeyword.internalNode(),
                expression.internalNode(),
                semicolonToken.internalNode());
        return stReturnStatement.createUnlinkedFacade();
    }

    public static BinaryExpressionNode createBinaryExpression(
            SyntaxKind kind,
            Node lhsExpr,
            Token operator,
            Node rhsExpr) {
        Objects.requireNonNull(lhsExpr, "lhsExpr must not be null");
        Objects.requireNonNull(operator, "operator must not be null");
        Objects.requireNonNull(rhsExpr, "rhsExpr must not be null");

        STNode stBinaryExpression = STNodeFactory.createBinaryExpressionNode(
                kind,
                lhsExpr.internalNode(),
                operator.internalNode(),
                rhsExpr.internalNode());
        return stBinaryExpression.createUnlinkedFacade();
    }

    public static BracedExpressionNode createBracedExpression(
            SyntaxKind kind,
            Token openParen,
            Node expression,
            Token closeParen) {
        Objects.requireNonNull(openParen, "openParen must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(closeParen, "closeParen must not be null");

        STNode stBracedExpression = STNodeFactory.createBracedExpressionNode(
                kind,
                openParen.internalNode(),
                expression.internalNode(),
                closeParen.internalNode());
        return stBracedExpression.createUnlinkedFacade();
    }

    public static CheckExpressionNode createCheckExpression(
            Token checkKeyword,
            Node expression) {
        Objects.requireNonNull(checkKeyword, "checkKeyword must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stCheckExpression = STNodeFactory.createCheckExpressionNode(
                checkKeyword.internalNode(),
                expression.internalNode());
        return stCheckExpression.createUnlinkedFacade();
    }

    public static FieldAccessExpressionNode createFieldAccessExpression(
            Node expression,
            Token dotToken,
            Token fieldName) {
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(dotToken, "dotToken must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null");

        STNode stFieldAccessExpression = STNodeFactory.createFieldAccessExpressionNode(
                expression.internalNode(),
                dotToken.internalNode(),
                fieldName.internalNode());
        return stFieldAccessExpression.createUnlinkedFacade();
    }

    public static FunctionCallExpressionNode createFunctionCallExpression(
            Node functionName,
            Token openParenToken,
            NodeList<FunctionArgumentNode> arguments,
            Token closeParenToken) {
        Objects.requireNonNull(functionName, "functionName must not be null");
        Objects.requireNonNull(openParenToken, "openParenToken must not be null");
        Objects.requireNonNull(arguments, "arguments must not be null");
        Objects.requireNonNull(closeParenToken, "closeParenToken must not be null");

        STNode stFunctionCallExpression = STNodeFactory.createFunctionCallExpressionNode(
                functionName.internalNode(),
                openParenToken.internalNode(),
                arguments.underlyingListNode().internalNode(),
                closeParenToken.internalNode());
        return stFunctionCallExpression.createUnlinkedFacade();
    }

    public static MethodCallExpressionNode createMethodCallExpression(
            ExpressionNode expression,
            Token dotToken,
            Token methodName,
            Token openParenToken,
            NodeList<FunctionArgumentNode> arguments,
            Token closeParenToken) {
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(dotToken, "dotToken must not be null");
        Objects.requireNonNull(methodName, "methodName must not be null");
        Objects.requireNonNull(openParenToken, "openParenToken must not be null");
        Objects.requireNonNull(arguments, "arguments must not be null");
        Objects.requireNonNull(closeParenToken, "closeParenToken must not be null");

        STNode stMethodCallExpression = STNodeFactory.createMethodCallExpressionNode(
                expression.internalNode(),
                dotToken.internalNode(),
                methodName.internalNode(),
                openParenToken.internalNode(),
                arguments.underlyingListNode().internalNode(),
                closeParenToken.internalNode());
        return stMethodCallExpression.createUnlinkedFacade();
    }

    public static MappingConstructorExpressionNode createMappingConstructorExpression(
            Token openBrace,
            NodeList<MappingFieldNode> fields,
            Token closeBrace) {
        Objects.requireNonNull(openBrace, "openBrace must not be null");
        Objects.requireNonNull(fields, "fields must not be null");
        Objects.requireNonNull(closeBrace, "closeBrace must not be null");

        STNode stMappingConstructorExpression = STNodeFactory.createMappingConstructorExpressionNode(
                openBrace.internalNode(),
                fields.underlyingListNode().internalNode(),
                closeBrace.internalNode());
        return stMappingConstructorExpression.createUnlinkedFacade();
    }

    public static MemberAccessExpressionNode createMemberAccessExpression(
            ExpressionNode containerExpression,
            Token openBracket,
            ExpressionNode keyExpression,
            Token closeBracket) {
        Objects.requireNonNull(containerExpression, "containerExpression must not be null");
        Objects.requireNonNull(openBracket, "openBracket must not be null");
        Objects.requireNonNull(keyExpression, "keyExpression must not be null");
        Objects.requireNonNull(closeBracket, "closeBracket must not be null");

        STNode stMemberAccessExpression = STNodeFactory.createMemberAccessExpressionNode(
                containerExpression.internalNode(),
                openBracket.internalNode(),
                keyExpression.internalNode(),
                closeBracket.internalNode());
        return stMemberAccessExpression.createUnlinkedFacade();
    }

    public static TypeofExpressionNode createTypeofExpression(
            Token typeofKeyword,
            Node expression) {
        Objects.requireNonNull(typeofKeyword, "typeofKeyword must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stTypeofExpression = STNodeFactory.createTypeofExpressionNode(
                typeofKeyword.internalNode(),
                expression.internalNode());
        return stTypeofExpression.createUnlinkedFacade();
    }

    public static UnaryExpressionNode createUnaryExpression(
            Token unaryOperator,
            Node expression) {
        Objects.requireNonNull(unaryOperator, "unaryOperator must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stUnaryExpression = STNodeFactory.createUnaryExpressionNode(
                unaryOperator.internalNode(),
                expression.internalNode());
        return stUnaryExpression.createUnlinkedFacade();
    }

    public static ComputedNameFieldNode createComputedNameField(
            Token leadingComma,
            Token openBracket,
            Node fieldNameExpr,
            Token closeBracket,
            Token colonToken,
            Node valueExpr) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(openBracket, "openBracket must not be null");
        Objects.requireNonNull(fieldNameExpr, "fieldNameExpr must not be null");
        Objects.requireNonNull(closeBracket, "closeBracket must not be null");
        Objects.requireNonNull(colonToken, "colonToken must not be null");
        Objects.requireNonNull(valueExpr, "valueExpr must not be null");

        STNode stComputedNameField = STNodeFactory.createComputedNameFieldNode(
                leadingComma.internalNode(),
                openBracket.internalNode(),
                fieldNameExpr.internalNode(),
                closeBracket.internalNode(),
                colonToken.internalNode(),
                valueExpr.internalNode());
        return stComputedNameField.createUnlinkedFacade();
    }

    public static ConstantDeclarationNode createConstantDeclaration(
            MetadataNode metadata,
            Token visibilityQualifier,
            Token constKeyword,
            Node typeDescriptor,
            Token variableName,
            Token equalsToken,
            Node initializer,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(constKeyword, "constKeyword must not be null");
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
        Objects.requireNonNull(variableName, "variableName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(initializer, "initializer must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stConstantDeclaration = STNodeFactory.createConstantDeclarationNode(
                metadata.internalNode(),
                visibilityQualifier.internalNode(),
                constKeyword.internalNode(),
                typeDescriptor.internalNode(),
                variableName.internalNode(),
                equalsToken.internalNode(),
                initializer.internalNode(),
                semicolonToken.internalNode());
        return stConstantDeclaration.createUnlinkedFacade();
    }

    public static DefaultableParameterNode createDefaultableParameter(
            Token leadingComma,
            NodeList<AnnotationNode> annotations,
            Token visibilityQualifier,
            Node type,
            Token paramName,
            Token equalsToken,
            Node expression) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(paramName, "paramName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stDefaultableParameter = STNodeFactory.createDefaultableParameterNode(
                leadingComma.internalNode(),
                annotations.underlyingListNode().internalNode(),
                visibilityQualifier.internalNode(),
                type.internalNode(),
                paramName.internalNode(),
                equalsToken.internalNode(),
                expression.internalNode());
        return stDefaultableParameter.createUnlinkedFacade();
    }

    public static RequiredParameterNode createRequiredParameter(
            Token leadingComma,
            NodeList<AnnotationNode> annotations,
            Token visibilityQualifier,
            Node type,
            Token paramName) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(paramName, "paramName must not be null");

        STNode stRequiredParameter = STNodeFactory.createRequiredParameterNode(
                leadingComma.internalNode(),
                annotations.underlyingListNode().internalNode(),
                visibilityQualifier.internalNode(),
                type.internalNode(),
                paramName.internalNode());
        return stRequiredParameter.createUnlinkedFacade();
    }

    public static RestParameterNode createRestParameter(
            Token leadingComma,
            NodeList<AnnotationNode> annotations,
            Node type,
            Token ellipsisToken,
            Token paramName) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(ellipsisToken, "ellipsisToken must not be null");
        Objects.requireNonNull(paramName, "paramName must not be null");

        STNode stRestParameter = STNodeFactory.createRestParameterNode(
                leadingComma.internalNode(),
                annotations.underlyingListNode().internalNode(),
                type.internalNode(),
                ellipsisToken.internalNode(),
                paramName.internalNode());
        return stRestParameter.createUnlinkedFacade();
    }

    public static ExpressionListItemNode createExpressionListItem(
            Token leadingComma,
            Node expression) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stExpressionListItem = STNodeFactory.createExpressionListItemNode(
                leadingComma.internalNode(),
                expression.internalNode());
        return stExpressionListItem.createUnlinkedFacade();
    }

    public static ImportOrgNameNode createImportOrgName(
            Token orgName,
            Token slashToken) {
        Objects.requireNonNull(orgName, "orgName must not be null");
        Objects.requireNonNull(slashToken, "slashToken must not be null");

        STNode stImportOrgName = STNodeFactory.createImportOrgNameNode(
                orgName.internalNode(),
                slashToken.internalNode());
        return stImportOrgName.createUnlinkedFacade();
    }

    public static ImportPrefixNode createImportPrefix(
            Token asKeyword,
            Token prefix) {
        Objects.requireNonNull(asKeyword, "asKeyword must not be null");
        Objects.requireNonNull(prefix, "prefix must not be null");

        STNode stImportPrefix = STNodeFactory.createImportPrefixNode(
                asKeyword.internalNode(),
                prefix.internalNode());
        return stImportPrefix.createUnlinkedFacade();
    }

    public static ImportSubVersionNode createImportSubVersion(
            Token leadingDot,
            Token versionNumber) {
        Objects.requireNonNull(leadingDot, "leadingDot must not be null");
        Objects.requireNonNull(versionNumber, "versionNumber must not be null");

        STNode stImportSubVersion = STNodeFactory.createImportSubVersionNode(
                leadingDot.internalNode(),
                versionNumber.internalNode());
        return stImportSubVersion.createUnlinkedFacade();
    }

    public static ImportVersionNode createImportVersion(
            Token versionKeyword,
            Node versionNumber) {
        Objects.requireNonNull(versionKeyword, "versionKeyword must not be null");
        Objects.requireNonNull(versionNumber, "versionNumber must not be null");

        STNode stImportVersion = STNodeFactory.createImportVersionNode(
                versionKeyword.internalNode(),
                versionNumber.internalNode());
        return stImportVersion.createUnlinkedFacade();
    }

    public static SubModuleNameNode createSubModuleName(
            Token leadingDot,
            IdentifierToken moduleName) {
        Objects.requireNonNull(leadingDot, "leadingDot must not be null");
        Objects.requireNonNull(moduleName, "moduleName must not be null");

        STNode stSubModuleName = STNodeFactory.createSubModuleNameNode(
                leadingDot.internalNode(),
                moduleName.internalNode());
        return stSubModuleName.createUnlinkedFacade();
    }

    public static SpecificFieldNode createSpecificField(
            Token leadingComma,
            IdentifierToken fieldName,
            Token colon,
            ExpressionNode valueExpr) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null");
        Objects.requireNonNull(colon, "colon must not be null");
        Objects.requireNonNull(valueExpr, "valueExpr must not be null");

        STNode stSpecificField = STNodeFactory.createSpecificFieldNode(
                leadingComma.internalNode(),
                fieldName.internalNode(),
                colon.internalNode(),
                valueExpr.internalNode());
        return stSpecificField.createUnlinkedFacade();
    }

    public static SpreadFieldNode createSpreadField(
            Token leadingComma,
            Token ellipsis,
            ExpressionNode valueExpr) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(ellipsis, "ellipsis must not be null");
        Objects.requireNonNull(valueExpr, "valueExpr must not be null");

        STNode stSpreadField = STNodeFactory.createSpreadFieldNode(
                leadingComma.internalNode(),
                ellipsis.internalNode(),
                valueExpr.internalNode());
        return stSpreadField.createUnlinkedFacade();
    }

    public static NamedArgumentNode createNamedArgument(
            Token leadingComma,
            Token argumentName,
            Token equalsToken,
            ExpressionNode expression) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(argumentName, "argumentName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stNamedArgument = STNodeFactory.createNamedArgumentNode(
                leadingComma.internalNode(),
                argumentName.internalNode(),
                equalsToken.internalNode(),
                expression.internalNode());
        return stNamedArgument.createUnlinkedFacade();
    }

    public static PositionalArgumentNode createPositionalArgument(
            Token leadingComma,
            ExpressionNode expression) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stPositionalArgument = STNodeFactory.createPositionalArgumentNode(
                leadingComma.internalNode(),
                expression.internalNode());
        return stPositionalArgument.createUnlinkedFacade();
    }

    public static RestArgumentNode createRestArgument(
            Token leadingComma,
            Token ellipsis,
            ExpressionNode expression) {
        Objects.requireNonNull(leadingComma, "leadingComma must not be null");
        Objects.requireNonNull(ellipsis, "ellipsis must not be null");
        Objects.requireNonNull(expression, "expression must not be null");

        STNode stRestArgument = STNodeFactory.createRestArgumentNode(
                leadingComma.internalNode(),
                ellipsis.internalNode(),
                expression.internalNode());
        return stRestArgument.createUnlinkedFacade();
    }

    public static ObjectTypeDescriptorNode createObjectTypeDescriptor(
            NodeList<Token> objectTypeQualifiers,
            Token objectKeyword,
            Token openBrace,
            NodeList<Node> members,
            Token closeBrace) {
        Objects.requireNonNull(objectTypeQualifiers, "objectTypeQualifiers must not be null");
        Objects.requireNonNull(objectKeyword, "objectKeyword must not be null");
        Objects.requireNonNull(openBrace, "openBrace must not be null");
        Objects.requireNonNull(members, "members must not be null");
        Objects.requireNonNull(closeBrace, "closeBrace must not be null");

        STNode stObjectTypeDescriptor = STNodeFactory.createObjectTypeDescriptorNode(
                objectTypeQualifiers.underlyingListNode().internalNode(),
                objectKeyword.internalNode(),
                openBrace.internalNode(),
                members.underlyingListNode().internalNode(),
                closeBrace.internalNode());
        return stObjectTypeDescriptor.createUnlinkedFacade();
    }

    public static RecordTypeDescriptorNode createRecordTypeDescriptor(
            Token objectKeyword,
            Token bodyStartDelimiter,
            NodeList<Node> fields,
            Token bodyEndDelimiter) {
        Objects.requireNonNull(objectKeyword, "objectKeyword must not be null");
        Objects.requireNonNull(bodyStartDelimiter, "bodyStartDelimiter must not be null");
        Objects.requireNonNull(fields, "fields must not be null");
        Objects.requireNonNull(bodyEndDelimiter, "bodyEndDelimiter must not be null");

        STNode stRecordTypeDescriptor = STNodeFactory.createRecordTypeDescriptorNode(
                objectKeyword.internalNode(),
                bodyStartDelimiter.internalNode(),
                fields.underlyingListNode().internalNode(),
                bodyEndDelimiter.internalNode());
        return stRecordTypeDescriptor.createUnlinkedFacade();
    }

    public static ReturnTypeDescriptorNode createReturnTypeDescriptor(
            Token returnsKeyword,
            NodeList<AnnotationNode> annotations,
            Node type) {
        Objects.requireNonNull(returnsKeyword, "returnsKeyword must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");
        Objects.requireNonNull(type, "type must not be null");

        STNode stReturnTypeDescriptor = STNodeFactory.createReturnTypeDescriptorNode(
                returnsKeyword.internalNode(),
                annotations.underlyingListNode().internalNode(),
                type.internalNode());
        return stReturnTypeDescriptor.createUnlinkedFacade();
    }

    public static NilTypeDescriptorNode createNilTypeDescriptor(
            Token openParenToken,
            Token closeParenToken) {
        Objects.requireNonNull(openParenToken, "openParenToken must not be null");
        Objects.requireNonNull(closeParenToken, "closeParenToken must not be null");

        STNode stNilTypeDescriptor = STNodeFactory.createNilTypeDescriptorNode(
                openParenToken.internalNode(),
                closeParenToken.internalNode());
        return stNilTypeDescriptor.createUnlinkedFacade();
    }

    public static OptionalTypeDescriptorNode createOptionalTypeDescriptor(
            Node typeDescriptor,
            Token questionMarkToken) {
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
        Objects.requireNonNull(questionMarkToken, "questionMarkToken must not be null");

        STNode stOptionalTypeDescriptor = STNodeFactory.createOptionalTypeDescriptorNode(
                typeDescriptor.internalNode(),
                questionMarkToken.internalNode());
        return stOptionalTypeDescriptor.createUnlinkedFacade();
    }

    public static ObjectFieldNode createObjectField(
            MetadataNode metadata,
            Token visibilityQualifier,
            Node type,
            Token fieldName,
            Token equalsToken,
            ExpressionNode expression,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(visibilityQualifier, "visibilityQualifier must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stObjectField = STNodeFactory.createObjectFieldNode(
                metadata.internalNode(),
                visibilityQualifier.internalNode(),
                type.internalNode(),
                fieldName.internalNode(),
                equalsToken.internalNode(),
                expression.internalNode(),
                semicolonToken.internalNode());
        return stObjectField.createUnlinkedFacade();
    }

    public static RecordFieldNode createRecordField(
            MetadataNode metadata,
            Node type,
            Token fieldName,
            Token questionMarkToken,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null");
        Objects.requireNonNull(questionMarkToken, "questionMarkToken must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stRecordField = STNodeFactory.createRecordFieldNode(
                metadata.internalNode(),
                type.internalNode(),
                fieldName.internalNode(),
                questionMarkToken.internalNode(),
                semicolonToken.internalNode());
        return stRecordField.createUnlinkedFacade();
    }

    public static RecordFieldWithDefaultValueNode createRecordFieldWithDefaultValue(
            MetadataNode metadata,
            Node type,
            Token fieldName,
            Token equalsToken,
            ExpressionNode expression,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stRecordFieldWithDefaultValue = STNodeFactory.createRecordFieldWithDefaultValueNode(
                metadata.internalNode(),
                type.internalNode(),
                fieldName.internalNode(),
                equalsToken.internalNode(),
                expression.internalNode(),
                semicolonToken.internalNode());
        return stRecordFieldWithDefaultValue.createUnlinkedFacade();
    }

    public static RecordRestDescriptorNode createRecordRestDescriptor(
            Node type,
            Token ellipsisToken,
            Token semicolonToken) {
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(ellipsisToken, "ellipsisToken must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stRecordRestDescriptor = STNodeFactory.createRecordRestDescriptorNode(
                type.internalNode(),
                ellipsisToken.internalNode(),
                semicolonToken.internalNode());
        return stRecordRestDescriptor.createUnlinkedFacade();
    }

    public static TypeReferenceNode createTypeReference(
            Token asteriskToken,
            Node type,
            Token semicolonToken) {
        Objects.requireNonNull(asteriskToken, "asteriskToken must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stTypeReference = STNodeFactory.createTypeReferenceNode(
                asteriskToken.internalNode(),
                type.internalNode(),
                semicolonToken.internalNode());
        return stTypeReference.createUnlinkedFacade();
    }

    public static QualifiedIdentifierNode createQualifiedIdentifier(
            Token modulePrefix,
            Node colon,
            IdentifierToken identifier) {
        Objects.requireNonNull(modulePrefix, "modulePrefix must not be null");
        Objects.requireNonNull(colon, "colon must not be null");
        Objects.requireNonNull(identifier, "identifier must not be null");

        STNode stQualifiedIdentifier = STNodeFactory.createQualifiedIdentifierNode(
                modulePrefix.internalNode(),
                colon.internalNode(),
                identifier.internalNode());
        return stQualifiedIdentifier.createUnlinkedFacade();
    }

    public static ServiceBodyNode createServiceBody(
            Token openBraceToken,
            NodeList<Node> resources,
            Token closeBraceToken) {
        Objects.requireNonNull(openBraceToken, "openBraceToken must not be null");
        Objects.requireNonNull(resources, "resources must not be null");
        Objects.requireNonNull(closeBraceToken, "closeBraceToken must not be null");

        STNode stServiceBody = STNodeFactory.createServiceBodyNode(
                openBraceToken.internalNode(),
                resources.underlyingListNode().internalNode(),
                closeBraceToken.internalNode());
        return stServiceBody.createUnlinkedFacade();
    }

    public static AnnotationNode createAnnotation(
            Token atToken,
            Node annotReference,
            MappingConstructorExpressionNode annotValue) {
        Objects.requireNonNull(atToken, "atToken must not be null");
        Objects.requireNonNull(annotReference, "annotReference must not be null");
        Objects.requireNonNull(annotValue, "annotValue must not be null");

        STNode stAnnotation = STNodeFactory.createAnnotationNode(
                atToken.internalNode(),
                annotReference.internalNode(),
                annotValue.internalNode());
        return stAnnotation.createUnlinkedFacade();
    }

    public static MetadataNode createMetadata(
            Node documentationString,
            NodeList<AnnotationNode> annotations) {
        Objects.requireNonNull(documentationString, "documentationString must not be null");
        Objects.requireNonNull(annotations, "annotations must not be null");

        STNode stMetadata = STNodeFactory.createMetadataNode(
                documentationString.internalNode(),
                annotations.underlyingListNode().internalNode());
        return stMetadata.createUnlinkedFacade();
    }

    public static ModuleVariableDeclarationNode createModuleVariableDeclaration(
            MetadataNode metadata,
            Token finalKeyword,
            Node typeName,
            Token variableName,
            Token equalsToken,
            ExpressionNode initializer,
            Token semicolonToken) {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(finalKeyword, "finalKeyword must not be null");
        Objects.requireNonNull(typeName, "typeName must not be null");
        Objects.requireNonNull(variableName, "variableName must not be null");
        Objects.requireNonNull(equalsToken, "equalsToken must not be null");
        Objects.requireNonNull(initializer, "initializer must not be null");
        Objects.requireNonNull(semicolonToken, "semicolonToken must not be null");

        STNode stModuleVariableDeclaration = STNodeFactory.createModuleVariableDeclarationNode(
                metadata.internalNode(),
                finalKeyword.internalNode(),
                typeName.internalNode(),
                variableName.internalNode(),
                equalsToken.internalNode(),
                initializer.internalNode(),
                semicolonToken.internalNode());
        return stModuleVariableDeclaration.createUnlinkedFacade();
    }

    public static IsExpressionNode createIsExpression(
            Node expression,
            Token isKeyword,
            Node typeDescriptor) {
        Objects.requireNonNull(expression, "expression must not be null");
        Objects.requireNonNull(isKeyword, "isKeyword must not be null");
        Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");

        STNode stIsExpression = STNodeFactory.createIsExpressionNode(
                expression.internalNode(),
                isKeyword.internalNode(),
                typeDescriptor.internalNode());
        return stIsExpression.createUnlinkedFacade();
    }
}

