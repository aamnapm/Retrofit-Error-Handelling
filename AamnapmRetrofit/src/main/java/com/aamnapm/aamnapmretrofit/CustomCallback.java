package com.aamnapm.aamnapmretrofit;

import java.io.IOException;

import retrofit2.Response;

public interface CustomCallback<T> {

    /**
     * <h3>Called for (200, 300) responses.</h3>
     *
     * @param code     Http status code
     * @param response Api response
     */
    void success(int code, Response<T> response);

    /**
     * <h3>Called for 401 responses.</h3>
     *
     * @param code     Http status code
     * @param response Api response
     */
    void unauthenticated(int code, Response<?> response);

    /**
     * <h3>Called for (400, 500) responses, <b>except 401</b>.</h3>
     *
     * @param code     Http status code
     * @param response Api response
     */
    void clientError(int code, Response<?> response);

    /**
     * <h3>Called for (500, 600) response.</h3>
     *
     * @param code     Http status code
     * @param response Api response
     */
    void serverError(int code, Response<?> response);

    /**
     * <h3>Called for network errors while making the call.</h3>
     *
     * @param e IOException
     */
    void networkError(IOException e);

    /**
     * <h3>Called for unexpected errors while making the call.</h3>
     *
     * @param t Throwable
     */
    void unexpectedError(Throwable t);

}
