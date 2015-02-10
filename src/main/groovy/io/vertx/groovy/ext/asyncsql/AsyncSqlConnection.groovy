/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.ext.asyncsql;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.ext.asyncsql.ResultSet
import io.vertx.core.json.JsonArray
import io.vertx.ext.asyncsql.UpdateResult
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
/**
 * @author <a href="http://www.campudus.com">Joern Bernhardt</a>.
 */
@CompileStatic
public class AsyncSqlConnection {
  final def io.vertx.ext.asyncsql.AsyncSqlConnection delegate;
  public AsyncSqlConnection(io.vertx.ext.asyncsql.AsyncSqlConnection delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Sets the auto commit flag for this connection. True by default. Set to false if you want to start a transaction.
   * <p>
   * If you change autoCommit from false to true, it will commit the running transaction. If you change it from false to
   * true, it will start a new transaction. If the autoCommit flag doesn't change, it will just call the resultHandler
   * with a success.
   *
   * @param autoCommit    the autoCommit flag, true by default.
   * @param resultHandler The handler which is called once this operation completes.
   * @see java.sql.Connection#setAutoCommit(boolean)
   */
  public AsyncSqlConnection setAutoCommit(boolean autoCommit, Handler<AsyncResult<Void>> resultHandler) {
    this.delegate.setAutoCommit(autoCommit, resultHandler);
    return this;
  }
  /**
   * Executes the given SQL statement.
   *
   * @param sql           The SQL to execute. For example <code>CREATE TABLE IF EXISTS table ...</code>
   * @param resultHandler The handler which is called once this operation completes.
   */
  public AsyncSqlConnection execute(String sql, Handler<AsyncResult<Void>> resultHandler) {
    this.delegate.execute(sql, resultHandler);
    return this;
  }
  /**
   * Executes the given SQL <code>SELECT</code> statement which returns the results of the query.
   *
   * @param sql           The SQL to execute. For example <code>SELECT * FROM mytable</code>.
   * @param resultHandler The handler which is called once the operation completes. It will return a list of
   *                      <code>JsonObject</code>'s which represent the ResultSet. So column names are keys, and values
   *                      are of course values.
   */
  public AsyncSqlConnection query(String sql, Handler<AsyncResult<ResultSet>> resultHandler) {
    this.delegate.query(sql, resultHandler);
    return this;
  }
  /**
   * Executes the given SQL <code>SELECT</code> statement which returns the results of the query. It will use a prepared
   * statement to pass the parameters.
   *
   * @param sql           The SQL to execute. For example <code>SELECT * FROM mytable WHERE id=?</code>.
   * @param params        These are the parameters to fill the statement.
   * @param resultHandler The handler which is called once the operation completes. It will return a list of
   *                      <code>JsonObject</code>'s which represent the ResultSet. So column names are keys, and values
   *                      are of course values.
   */
  public AsyncSqlConnection queryWithParams(String sql, List<Object> params, Handler<AsyncResult<ResultSet>> resultHandler) {
    this.delegate.queryWithParams(sql, params != null ? new io.vertx.core.json.JsonArray(params) : null, resultHandler);
    return this;
  }
  /**
   * Executes the given SQL statement which may be an <code>INSERT</code>, <code>UPDATE</code>, or <code>DELETE</code>
   * statement.
   *
   * @param sql           The SQL to execute. For example <code>INSERT INTO table ...</code>
   * @param resultHandler The handler which is called once the operation completes.
   */
  public AsyncSqlConnection update(String sql, Handler<AsyncResult<UpdateResult>> resultHandler) {
    this.delegate.update(sql, resultHandler);
    return this;
  }
  /**
   * Executes the given SQL statement which may be an <code>INSERT</code>, <code>UPDATE</code>, or <code>DELETE</code>
   * statement.
   *
   * @param sql           The SQL to execute. For example <code>INSERT INTO mytable ('name', 'age') VALUES (?,
   *                      ?)</code>
   * @param params        These are the parameters to fill the statement.
   * @param resultHandler The handler which is called once the operation completes.
   */
  public AsyncSqlConnection updateWithParams(String sql, List<Object> params, Handler<AsyncResult<UpdateResult>> resultHandler) {
    this.delegate.updateWithParams(sql, params != null ? new io.vertx.core.json.JsonArray(params) : null, resultHandler);
    return this;
  }
  /**
   * Closes the connection. Important to always close the connection when you are done so it's returned to the pool.
   *
   * @param handler The handler which is called once the operation completes.
   */
  public void close(Handler<AsyncResult<Void>> handler) {
    this.delegate.close(handler);
  }
  /**
   * Commits all changes made since the previous commit/rollback.
   *
   * @param handler The handler which is called once the operation completes.
   */
  public AsyncSqlConnection commit(Handler<AsyncResult<Void>> handler) {
    this.delegate.commit(handler);
    return this;
  }
  /**
   * Rolls back all changes made since the previous commit/rollback.
   *
   * @param handler The handler which is called once the operation completes.
   */
  public AsyncSqlConnection rollback(Handler<AsyncResult<Void>> handler) {
    this.delegate.rollback(handler);
    return this;
  }

  static final java.util.function.Function<io.vertx.ext.asyncsql.AsyncSqlConnection, AsyncSqlConnection> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.ext.asyncsql.AsyncSqlConnection arg -> new AsyncSqlConnection(arg);
  };
}
