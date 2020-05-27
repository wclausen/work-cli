package com.wclausen.work.kotlinext;

import java.lang.System;

/**
 * Enables forcing `when` statements to be exhaustive by turning them into expressions
 *
 * Usage:
 *  when ($sealedClassVar) {...}
 *  becomes
 *  Do exhaustive when ($sealedClassVar) {...}
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0086\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/wclausen/work/kotlinext/Do;", "", "()V", "exhaustive", "", "any", "work-cli"})
public final class Do {
    public static final com.wclausen.work.kotlinext.Do INSTANCE = null;
    
    public final void exhaustive(@org.jetbrains.annotations.Nullable()
    java.lang.Object any) {
    }
    
    private Do() {
        super();
    }
}