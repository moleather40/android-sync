/* Any copyright is dedicated to the Public Domain.
   http://creativecommons.org/publicdomain/zero/1.0/ */

package org.mozilla.android.sync.test.helpers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import junit.framework.AssertionFailedError;

import org.mozilla.gecko.sync.repositories.delegates.RepositorySessionFetchRecordsDelegate;
import org.mozilla.gecko.sync.repositories.domain.Record;

public class ExpectSuccessRepositorySessionFetchRecordsDelegate extends
    ExpectSuccessDelegate implements RepositorySessionFetchRecordsDelegate {
  public ArrayList<Record> fetchedRecords = new ArrayList<Record>();

  public ExpectSuccessRepositorySessionFetchRecordsDelegate(WaitHelper waitHelper) {
    super(waitHelper);
  }

  @Override
  public void onFetchFailed(Exception ex, Record record) {
    log("Fetch failed.", ex);
    performNotify(new AssertionFailedError("onFetchFailed: fetch should not have failed."));
  }

  @Override
  public void onFetchedRecord(Record record) {
    fetchedRecords.add(record);
    log("Fetched record with guid '" + record.guid + "'.");
  }

  @Override
  public void onFetchCompleted(long end) {
    log("Fetch completed.");
    performNotify();
  }

  @Override
  public void onFetchSucceeded(Record[] records, long end) {
    for (Record record : records) {
      this.onFetchedRecord(record);
    }
    this.onFetchCompleted(end);
  }

  @Override
  public RepositorySessionFetchRecordsDelegate deferredFetchDelegate(ExecutorService executor) {
    return this;
  }
}
