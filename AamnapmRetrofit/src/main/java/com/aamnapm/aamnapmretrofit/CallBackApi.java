package com.aamnapm.aamnapmretrofit;

public interface CallBackApi<T> {

    /**
     * @param statusCode Http status code
     * @param obj        Generic
     */
    void onSuccess(int statusCode, T obj);

    /**
     * @param statusCode Http status code
     * @param obj        Object
     */
    void serverError(int statusCode, Object obj);

    /**
     * @param obj Object
     */
    void networkError(Object obj);

    /**
     * @param statusCode Http status code
     * @param obj        Object
     */
    void unAuthenticated(int statusCode, Object obj);

    /**
     * @param statusCode Http status code
     * @param obj        Object
     */
    void clientError(int statusCode, Object obj);

    /**
     * @param obj Object
     */
    void unexpectedError(Object obj);

}
