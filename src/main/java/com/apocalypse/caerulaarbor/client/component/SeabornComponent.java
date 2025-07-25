package com.apocalypse.caerulaarbor.client.component;

import net.minecraft.network.chat.contents.TranslatableContents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class SeabornComponent extends TranslatableContents {
    public final boolean useObfuscatedText;

    public SeabornComponent(String pKey, boolean useObfuscatedText, @Nullable String pFallback, Object[] pArgs) {
        super(pKey, pFallback, pArgs);
        this.useObfuscatedText = useObfuscatedText;
    }

    @Override
    public @NotNull String toString() {
        return "seaborn{useObfuscatedText=" + this.useObfuscatedText + ", " + "key='" + this.getKey() + "'" + (this.getFallback() != null ? ", fallback='" + this.getFallback() + "'" : "") + ", args=" + Arrays.toString(this.getArgs()) + "}";
    }
}
