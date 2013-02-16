/*
 * Licensed to Luca Cavanna (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.shell.client;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.shell.client.builders.cluster.*;
import org.elasticsearch.shell.client.builders.core.*;
import org.elasticsearch.shell.client.builders.indices.*;
import org.elasticsearch.shell.json.JsonToString;
import org.elasticsearch.shell.json.StringToJson;

import java.io.Closeable;

/**
 * @author Luca Cavanna
 *
 * Generic elasticsearch client wrapper which exposes common client operations that don't depend
 * on the specific type of client in use (transport or node)
 *
 * @param <JsonInput> the shell native object that represents a json object received as input from the shell
 * @param <JsonOutput> the shell native object that represents a json object that we give as output to the shell
 */
@SuppressWarnings("unused")
public abstract class AbstractClient<JsonInput, JsonOutput> implements Closeable {

    private final Client client;
    private final JsonToString<JsonInput> jsonToString;
    private final StringToJson<JsonOutput> stringToJson;

    protected AbstractClient(Client client, JsonToString<JsonInput> jsonToString, StringToJson<JsonOutput> stringToJson) {
        this.client = client;
        this.jsonToString = jsonToString;
        this.stringToJson = stringToJson;
    }

    public CountRequestBuilder<JsonInput, JsonOutput> countBuilder() {
        return new CountRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput count() {
        return countBuilder().execute();
    }

    public JsonOutput count(JsonInput query) {
        return countBuilder().query(query).execute();
    }

    public DeleteRequestBuilder<JsonInput, JsonOutput> deleteBuilder() {
        return new DeleteRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput delete(String index, String type, String id) {
        return deleteBuilder().index(index).type(type).id(id).execute();
    }

    public DeleteByQueryRequestBuilder<JsonInput, JsonOutput> deleteByQueryBuilder() {
        return new DeleteByQueryRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput deleteByQuery(JsonInput query) {
        return deleteByQueryBuilder().query(query).execute();
    }

    public ExplainRequestBuilder<JsonInput, JsonOutput> explainBuilder() {
        return new ExplainRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput explain(String index, String type, String id, JsonInput source) {
        return explainBuilder().index(index).type(type).id(id).source(source).execute();
    }

    public GetRequestBuilder<JsonInput, JsonOutput> getBuilder() {
        return new GetRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput get(String index, String type, String id) {
        return getBuilder().index(index).type(type).id(id).execute();
    }

    public IndexRequestBuilder<JsonInput, JsonOutput> indexBuilder() {
        return new IndexRequestBuilder<JsonInput,JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput index(String index, String type, String id, JsonInput source) {
        return indexBuilder().index(index).type(type).id(id).source(source).execute();
    }

    public MoreLikeThisRequestBuilder<JsonInput, JsonOutput> moreLikeThisBuilder(String index) {
        return new MoreLikeThisRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson, index);
    }

    public JsonOutput moreLikeThis(String index, String type, String id) {
        return moreLikeThisBuilder(index).type(type).id(id).execute();
    }

    public PercolateRequestBuilder<JsonInput, JsonOutput> percolateBuilder() {
        return new PercolateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput percolate(String index, String type, JsonInput source) {
        return percolateBuilder().index(index).type(type).source(source).execute();
    }

    public SearchRequestBuilder<JsonInput, JsonOutput> searchBuilder() {
        return new SearchRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput search() {
        return searchBuilder().execute();
    }

    public JsonOutput search(JsonInput source) {
        return searchBuilder().source(source).execute();
    }

    public UpdateRequestBuilder<JsonInput, JsonOutput> updateBuilder() {
        return new UpdateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput update(String index, String type, String id, JsonInput doc) {
        return updateBuilder().index(index).type(type).id(id).doc(doc).execute();
    }

    public JsonOutput update(String index, String type, String id, String script) {
        return updateBuilder().index(index).type(type).id(id).script(script).execute();
    }

    public ValidateQueryRequestBuilder<JsonInput, JsonOutput> validateBuilder() {
        return new ValidateQueryRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput validate(JsonInput query) {
        return validateBuilder().query(query).execute();
    }

    /*
    Indices APIs
     */
    public GetAliasesIndicesRequestBuilder<JsonInput, JsonOutput> aliasesGetBuilder() {
        return new GetAliasesIndicesRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput aliasesGet() {
        return aliasesGetBuilder().execute();
    }

    public UpdateIndicesAliasesRequestBuilder<JsonInput, JsonOutput> aliasesUpdateBuilder() {
        return new UpdateIndicesAliasesRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public AnalyzeRequestBuilder<JsonInput, JsonOutput> analyzeBuilder() {
        return new AnalyzeRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public ClearCacheRequestBuilder<JsonInput, JsonOutput> clearCacheBuilder() {
        return new ClearCacheRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clearCache() {
        return clearCacheBuilder().execute();
    }

    public CloseIndexRequestBuilder<JsonInput, JsonOutput> closeIndexBuilder() {
        return new CloseIndexRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput closeIndex(String index) {
        return closeIndexBuilder().index(index).execute();
    }

    public CreateIndexRequestBuilder<JsonInput, JsonOutput> createIndexBuilder() {
        return new CreateIndexRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput createIndex(String index) {
        return createIndexBuilder().index(index).execute();
    }

    public DeleteIndexRequestBuilder<JsonInput, JsonOutput> deleteIndexBuilder() {
        return new DeleteIndexRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput deleteIndex() {
        return deleteIndexBuilder().execute();
    }

    public FlushRequestBuilder<JsonInput, JsonOutput> flushBuilder() {
        return new FlushRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public IndicesExistsRequestBuilder<JsonInput, JsonOutput> indicesExistsBuilder() {
        return new IndicesExistsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput indicesExists(String... indices) {
        return indicesExistsBuilder().indices(indices).execute();
    }

    public GetMappingRequestBuilder<JsonInput, JsonOutput> mappingGetBuilder() {
        return new GetMappingRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput mappingGet(String... indices) {
        return mappingGetBuilder().indices(indices).execute();
    }

    public DeleteMappingRequestBuilder<JsonInput, JsonOutput> mappingDeleteBuilder() {
        return new DeleteMappingRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput mappingDelete(String index, String type) {
        return mappingDeleteBuilder().indices(index).type(type).execute();
    }

    public PutMappingRequestBuilder<JsonInput, JsonOutput> mappingPutBuilder() {
        return new PutMappingRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput mappingPut(String index, String type, JsonInput source) {
        return mappingPutBuilder().indices(index).type(type).source(source).execute();
    }

    public OpenIndexRequestBuilder<JsonInput, JsonOutput> openIndexBuilder() {
        return new OpenIndexRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput openIndex(String index) {
        return openIndexBuilder().index(index).execute();
    }

    public OptimizeRequestBuilder<JsonInput, JsonOutput> optimizeBuilder() {
        return new OptimizeRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput optimize(String... indices) {
        return optimizeBuilder().indices(indices).execute();
    }

    public RefreshRequestBuilder<JsonInput, JsonOutput> refreshBuilder() {
        return new RefreshRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput refresh(String... indices) {
        return refreshBuilder().indices(indices).execute();
    }

    public SegmentsRequestBuilder<JsonInput, JsonOutput> segmentsBuilder() {
        return new SegmentsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput segments(String... indices) {
        return segmentsBuilder().indices(indices).execute();
    }

    public GetSettingsRequestBuilder<JsonInput, JsonOutput> settingsGetBuilder() {
        return new GetSettingsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput settingsGet(String... indices) {
        return settingsGetBuilder().indices(indices).execute();
    }

    public UpdateSettingsRequestBuilder<JsonInput, JsonOutput> settingsUpdateBuilder() {
        return new UpdateSettingsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput settingsUpdate(String index, JsonInput source) {
        return settingsUpdateBuilder().indices(index).settings(source).execute();
    }

    public StatsRequestBuilder<JsonInput, JsonOutput> statsBuilder() {
        return new StatsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput stats(String... indices) {
        return statsBuilder().indices(indices).execute();
    }

    public StatusRequestBuilder<JsonInput, JsonOutput> statusBuilder() {
        return new StatusRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput status(String... indices) {
        return statusBuilder().indices(indices).execute();
    }

    public GetIndexTemplateRequestBuilder<JsonInput, JsonOutput> templateGetBuilder() {
        return new GetIndexTemplateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput templateGet(String... names) {
        return templateGetBuilder().names(names).execute();
    }

    public DeleteIndexTemplateRequestBuilder<JsonInput, JsonOutput> templateDeleteBuilder(String name) {
        return new DeleteIndexTemplateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson, name);
    }

    public JsonOutput templateDelete(String name) {
        return templateDeleteBuilder(name).execute();
    }

    public PutIndexTemplateRequestBuilder<JsonInput, JsonOutput> templatePutBuilder() {
        return new PutIndexTemplateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput templatePut(String name, JsonInput source) {
        return templatePutBuilder().template(name).source(source).execute();
    }

    public TypesExistsRequestBuilder<JsonInput, JsonOutput> typesExistsBuilder() {
        return new TypesExistsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput typesExists(String index, String type) {
        return typesExistsBuilder().indices(index).types(type).execute();
    }

    public GetWarmerRequestBuilder<JsonInput, JsonOutput> warmerGetBuilder() {
        return new GetWarmerRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput warmerGet(String... indices) {
        return warmerGetBuilder().indices(indices).execute();
    }

    public PutWarmerRequestBuilder<JsonInput, JsonOutput> warmerPutBuilder() {
        return new PutWarmerRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput warmerPut(String name, SearchRequest searchRequest) {
        return warmerPutBuilder().name(name).searchRequest(searchRequest).execute();
    }

    public DeleteWarmerRequestBuilder<JsonInput, JsonOutput> warmerDeleteBuilder() {
        return new DeleteWarmerRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput warmerDelete(String index, String name) {
        return warmerDeleteBuilder().indices(index).name(name).execute();
    }

    public JsonOutput warmerDelete(String index) {
        return warmerDeleteBuilder().indices(index).execute();
    }

    /*
    Cluster APIs
     */
    public ClusterHealthRequestBuilder<JsonInput, JsonOutput> clusterHealthBuilder() {
        return new ClusterHealthRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clusterHealth(String... indices) {
        return clusterHealthBuilder().indices(indices).execute();
    }

    public ClusterStateRequestBuilder<JsonInput, JsonOutput> clusterStateBuilder() {
        return new ClusterStateRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clusterState() {
        return clusterStateBuilder().execute();
    }

    public GetClusterSettingsRequestBuilder<JsonInput, JsonOutput> clusterSettingsGetBuilder() {
        return new GetClusterSettingsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clusterSettingsGet() {
        return clusterSettingsGetBuilder().execute();
    }

    public UpdateClusterSettingsRequestBuilder<JsonInput, JsonOutput> clusterSettingsUpdateBuilder() {
        return new UpdateClusterSettingsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clusterSettingsUpdate(JsonInput settings) {
        return clusterSettingsUpdateBuilder().transientSettings(settings).execute();
    }

    public NodesInfoRequestBuilder<JsonInput, JsonOutput> nodesInfoBuilder() {
        return new NodesInfoRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput nodesInfo() {
        return nodesInfoBuilder().execute();
    }

    public NodesStatsRequestBuilder<JsonInput, JsonOutput> nodesStatsBuilder() {
        return new NodesStatsRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput nodesStats() {
        return nodesStatsBuilder().execute();
    }

    public NodesHotThreadsRequestBuilder<JsonInput> nodesHotThreadsBuilder() {
        return new NodesHotThreadsRequestBuilder<JsonInput>(client, jsonToString);
    }

    public String nodesHotThreads() {
        return nodesHotThreadsBuilder().execute();
    }

    public ClusterRerouteRequestBuilder<JsonInput, JsonOutput> clusterRerouteBuilder() {
        return new ClusterRerouteRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput clusterReroute(JsonInput source) throws Exception {
        return clusterRerouteBuilder().source(source).execute();
    }

    public NodesRestartRequestBuilder<JsonInput, JsonOutput> nodesRestartBuilder() {
        return new NodesRestartRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput nodesRestart() throws Exception {
        return nodesRestartBuilder().execute();
    }

    public NodesShutdownRequestBuilder<JsonInput, JsonOutput> nodesShutdownBuilder() {
        return new NodesShutdownRequestBuilder<JsonInput, JsonOutput>(client, jsonToString, stringToJson);
    }

    public JsonOutput nodesShutdown(String... nodes) throws Exception {
        return nodesShutdownBuilder().nodesIds(nodes).execute();
    }


    Client client() {
        return client;
    }

    protected String jsonToString(JsonInput source) {
        return jsonToString.jsonToString(source, false);
    }

    @Override
    public String toString() {
        return asString();
    }

    /**
     * Returns the string representation of the object
     * Handy abstract method to avoid forgetting to implement toString
     * The string representation is important here since shown on the console
     * @return the string representation of the client
     */
    protected abstract String asString();
}