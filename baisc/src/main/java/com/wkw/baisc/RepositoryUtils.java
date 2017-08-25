package com.wkw.baisc;

import com.wkw.baisc.exception.NetworkConnectionException;
import com.wkw.baisc.exception.ResponseException;
import com.wkw.baisc.model.MrResponse;
import com.wkw.sdk.utils.Logger;
import com.wkw.sdk.utils.StringUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by wukewei on 2017/8/25.
 */

public class RepositoryUtils {
    private static final String TAG = "RepositoryUtils";
    private static final String DEFAULT_ERROR_MSG = "Unknown error";

    public static <T> ObservableTransformer<MrResponse<T>, T> handleResult() {
        return upstream -> upstream
                .flatMap((Function<MrResponse<T>, ObservableSource<T>>) tApiResponse -> {
                            if (tApiResponse == null) {
                                return Observable.error(new NetworkConnectionException());
                            } else if (tApiResponse.getStatusCode() != ResponseException.STATUS_CODE_SUCCESS) {
                                Logger.d(TAG, StringUtils.isEmpty(tApiResponse.getStatusMessage()) ? DEFAULT_ERROR_MSG : tApiResponse.getStatusMessage());
                                return Observable.error(new ResponseException(tApiResponse));
                            } else {
                                return createData(tApiResponse.getData());
                            }
                        }
                );
    }

    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
