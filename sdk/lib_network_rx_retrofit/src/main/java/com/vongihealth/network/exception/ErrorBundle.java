package com.vongihealth.network.exception;

/**
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 * Created by hzwukewei on 2017-1-22.
 */

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
