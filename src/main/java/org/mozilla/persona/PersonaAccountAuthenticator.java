/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.persona;

import org.mozilla.gecko.sync.Logger;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PersonaAccountAuthenticator extends AbstractAccountAuthenticator {
  public static final String LOG_TAG = "PersonaAccountAuth";

  public static final String ACCOUNT_TYPE_PERSONA = "org.mozilla.persona";

  private Context mContext;
  public PersonaAccountAuthenticator(Context context) {
    super(context);
    mContext = context;
  }

  @Override
  public Bundle addAccount(AccountAuthenticatorResponse response,
      String accountType, String authTokenType, String[] requiredFeatures,
      Bundle options) throws NetworkErrorException {
    Logger.debug(LOG_TAG, "addAccount()");
    final Intent intent = new Intent(mContext, PersonaAuthenticatorActivity.class);
    intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
    intent.putExtra("accountType", ACCOUNT_TYPE_PERSONA);

    final Bundle result = new Bundle();
    result.putParcelable(AccountManager.KEY_INTENT, intent);

    return result;
  }

  @Override
  public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                   Account account,
                                   Bundle options) throws NetworkErrorException {
    Logger.debug(LOG_TAG, "confirmCredentials()");
    return null;
  }

  @Override
  public Bundle editProperties(AccountAuthenticatorResponse response,
                               String accountType) {
    Logger.debug(LOG_TAG, "editProperties");
    return null;
  }

  @Override
  public Bundle getAuthToken(AccountAuthenticatorResponse response,
      Account account, String authTokenType, Bundle options)
      throws NetworkErrorException {
/*    Logger.debug(LOG_TAG, "getAuthToken()");
    if (!authTokenType.equals(Constants.AUTHTOKEN_TYPE_PLAIN)) {
      final Bundle result = new Bundle();
      result.putString(AccountManager.KEY_ERROR_MESSAGE,
          "invalid authTokenType");
      return result;
    }

    // Extract the username and password from the Account Manager, and ask
    // the server for an appropriate AuthToken.
    final AccountManager am = AccountManager.get(mContext);
    final String password = am.getPassword(account);
    if (password != null) {
      final Bundle result = new Bundle();

      // This is a Sync account.
      result.putString(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNTTYPE_SYNC);

      // Server.
      String serverURL = am.getUserData(account, Constants.OPTION_SERVER);
      result.putString(Constants.OPTION_SERVER, serverURL);

      // Full username, before hashing.
      result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);

      // Username after hashing.
      try {
        String username = Utils.usernameFromAccount(account.name);
        Logger.pii(LOG_TAG, "Account " + account.name + " hashes to " + username + ".");
        Logger.debug(LOG_TAG, "Setting username. Null? " + (username == null));
        result.putString(Constants.OPTION_USERNAME, username);
      } catch (NoSuchAlgorithmException e) {
        // Do nothing. Calling code must check for missing value.
      } catch (UnsupportedEncodingException e) {
        // Do nothing. Calling code must check for missing value.
      }

      // Sync key.
      final String syncKey = am.getUserData(account, Constants.OPTION_SYNCKEY);
      Logger.debug(LOG_TAG, "Setting sync key. Null? " + (syncKey == null));
      result.putString(Constants.OPTION_SYNCKEY, syncKey);

      // Password.
      result.putString(AccountManager.KEY_AUTHTOKEN, password);
      return result;
    }*/
    Logger.warn(LOG_TAG, "Returning null bundle for getAuthToken.");
    return null;
  }

  @Override
  public String getAuthTokenLabel(String authTokenType) {
    Logger.debug(LOG_TAG, "getAuthTokenLabel()");
    return null;
  }

  @Override
  public Bundle hasFeatures(AccountAuthenticatorResponse response,
      Account account, String[] features) throws NetworkErrorException {
    Logger.debug(LOG_TAG, "hasFeatures()");
    return null;
  }

  @Override
  public Bundle updateCredentials(AccountAuthenticatorResponse response,
      Account account, String authTokenType, Bundle options)
      throws NetworkErrorException {
    Logger.debug(LOG_TAG, "updateCredentials()");
    return null;
  }
}