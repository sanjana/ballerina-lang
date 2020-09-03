/*
 * Copyright (c) 2018, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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
package org.ballerinalang.langserver.completions.providers.context;

import io.ballerinalang.compiler.syntax.tree.ModulePartNode;
import io.ballerinalang.compiler.syntax.tree.NonTerminalNode;
import io.ballerinalang.compiler.syntax.tree.QualifiedNameReferenceNode;
import org.ballerinalang.annotation.JavaSPIService;
import org.ballerinalang.langserver.SnippetBlock;
import org.ballerinalang.langserver.common.utils.QNameReferenceUtil;
import org.ballerinalang.langserver.commons.LSContext;
import org.ballerinalang.langserver.commons.completion.CompletionKeys;
import org.ballerinalang.langserver.commons.completion.LSCompletionItem;
import org.ballerinalang.langserver.completions.SnippetCompletionItem;
import org.ballerinalang.langserver.completions.StaticCompletionItem;
import org.ballerinalang.langserver.completions.SymbolCompletionItem;
import org.ballerinalang.langserver.completions.providers.AbstractCompletionProvider;
import org.eclipse.lsp4j.CompletionItem;
import org.wso2.ballerinalang.compiler.semantics.model.Scope;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BPackageSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BTypeSymbol;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Completion provider for {@link ModulePartNode} context.
 *
 * @since 2.0.0
 */
@JavaSPIService("org.ballerinalang.langserver.commons.completion.spi.CompletionProvider")
public class ModulePartNodeContext extends AbstractCompletionProvider<ModulePartNode> {

    public ModulePartNodeContext() {
        super(ModulePartNode.class);
    }

    @Override
    public List<LSCompletionItem> getCompletions(LSContext context, ModulePartNode node) {
        NonTerminalNode nodeAtCursor = context.get(CompletionKeys.NODE_AT_CURSOR_KEY);
        if (this.onQualifiedNameIdentifier(context, nodeAtCursor)) {
            Predicate<Scope.ScopeEntry> predicate = scopeEntry -> scopeEntry.symbol instanceof BTypeSymbol;
            List<Scope.ScopeEntry> types = QNameReferenceUtil.getModuleContent(context,
                    (QualifiedNameReferenceNode) nodeAtCursor, predicate);
            return this.getCompletionItemList(types, context);
        }

        List<LSCompletionItem> completionItems = new ArrayList<>();
        completionItems.addAll(addTopLevelItems(context));
        completionItems.addAll(this.getTypeItems(context));
        completionItems.addAll(this.getPackagesCompletionItems(context));
        this.sort(context, node, completionItems);

        return completionItems;
    }

    @Override
    public void sort(LSContext context, ModulePartNode node, List<LSCompletionItem> items, Object... metaData) {
        for (LSCompletionItem item : items) {
            CompletionItem cItem = item.getCompletionItem();
            if (item instanceof SnippetCompletionItem
                    && (((SnippetCompletionItem) item).kind() == SnippetBlock.Kind.SNIPPET
                    || ((SnippetCompletionItem) item).kind() == SnippetBlock.Kind.STATEMENT)) {
                cItem.setSortText(this.genSortText(1));
                continue;
            }
            if (item instanceof SnippetCompletionItem
                    && ((SnippetCompletionItem) item).kind() == SnippetBlock.Kind.KEYWORD) {
                cItem.setSortText(this.genSortText(2));
                continue;
            }
            if (this.isModuleCompletionItem(item)) {
                cItem.setSortText(this.genSortText(3));
                continue;
            }
            if (this.isTypeCompletionItem(item)) {
                cItem.setSortText(this.genSortText(4));
                continue;
            }
            cItem.setSortText(this.genSortText(5));
        }
    }

    private boolean isModuleCompletionItem(LSCompletionItem item) {
        return (item instanceof SymbolCompletionItem
                && ((SymbolCompletionItem) item).getSymbol() instanceof BPackageSymbol)
                || (item instanceof StaticCompletionItem
                && (((StaticCompletionItem) item).kind() == StaticCompletionItem.Kind.MODULE
                || ((StaticCompletionItem) item).kind() == StaticCompletionItem.Kind.LANG_LIB_MODULE));
    }

    private boolean isTypeCompletionItem(LSCompletionItem item) {
        return (item instanceof SymbolCompletionItem
                && ((SymbolCompletionItem) item).getSymbol() instanceof BTypeSymbol)
                || (item instanceof StaticCompletionItem
                && ((StaticCompletionItem) item).kind() == StaticCompletionItem.Kind.TYPE);
    }
}
