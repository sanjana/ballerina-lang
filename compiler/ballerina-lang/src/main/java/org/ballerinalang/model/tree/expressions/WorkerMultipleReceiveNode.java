/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.model.tree.expressions;

import org.ballerinalang.model.tree.IdentifierNode;
import org.wso2.ballerinalang.compiler.tree.expressions.BLangExpression;
import org.wso2.ballerinalang.compiler.tree.expressions.BLangRecordLiteral;
import org.wso2.ballerinalang.compiler.tree.expressions.BLangWorkerMultipleReceive;

import java.util.List;
/**
 * Implementation of {@link BLangWorkerMultipleReceive}.
 *
 */
public interface WorkerMultipleReceiveNode extends ExpressionNode {
    BLangRecordLiteral getReceiveFieldsMapLiteral();

    List<WorkerReceiveFieldNode> getReceiveFields();

    /**
     * This static inner class represents worker name/worker field name of receive field collection.
     *
     */
    interface WorkerReceiveFieldNode {

        void setWorkerName(IdentifierNode identifierNode);

        void setWorkerFieldName(IdentifierNode identifierNode);

        ExpressionNode getKeyExpression();

        IdentifierNode getWorkerName();

        IdentifierNode getWorkerFieldName();

        void setSendExpression(BLangExpression sendExpression);

        BLangExpression getSendExpression();

        void setCurrentReceiveFieldVisited();

        boolean isCurrentReceiveFieldVisited();

    }


}
