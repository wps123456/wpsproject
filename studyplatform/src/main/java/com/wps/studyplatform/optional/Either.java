package com.wps.studyplatform.optional;

import java.util.Optional;

/**
 * @Title Either
 * @Description
 * @auther wps
 * @Date 2020/5/814:46
 */
public class Either<L,R> {
    public final Optional<L> left;
    public final Optional<R> right;

    public Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

}
