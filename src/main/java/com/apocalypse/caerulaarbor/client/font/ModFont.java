package com.apocalypse.caerulaarbor.client.font;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Random;
import java.util.zip.CRC32;

public class ModFont {

    public static final Style SEABORN_LANGUAGE = Style.EMPTY.withFont(CaerulaArborMod.loc("seaborn_language"));
    public static final Style SEABORN_LANGUAGE_INVERTED = Style.EMPTY.withFont(CaerulaArborMod.loc("seaborn_language_inverted"));

    private static Random getRandom(String string) {
        CRC32 crc32 = new CRC32();
        crc32.update(string.getBytes());
        long seed = crc32.getValue();
        return new Random(seed);
    }

    public static Component randomInvert(String text, boolean invert) {
        Random random = getRandom(text);
        MutableComponent component = Component.literal("");
        for (char c : text.toCharArray()) {
            component = component.append(Component.literal("" + c).withStyle(random.nextDouble() > 0.5 && invert ? SEABORN_LANGUAGE_INVERTED : SEABORN_LANGUAGE));
        }
        return component;
    }
}
