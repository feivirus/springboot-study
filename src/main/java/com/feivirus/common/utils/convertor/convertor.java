package com.feivirus.common.utils.convertor;

import com.feivirus.common.utils.entity.Result;

/**
 * @author feivirus
 */
public interface convertor<P, R> {
    Result  convert(P param, R result);

    default Result convert(P param, R result, Integer convertType) {
        return Result.newSuccess();
    }
}
