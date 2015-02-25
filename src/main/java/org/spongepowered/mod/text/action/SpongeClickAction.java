/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod.text.action;

import com.google.common.collect.ImmutableMap;
import net.minecraft.event.ClickEvent;
import org.spongepowered.api.text.action.ClickAction;

public final class SpongeClickAction {

    private SpongeClickAction() {}

    private static final ImmutableMap<Class<? extends ClickAction<?>>, ClickEvent.Action> TYPES =
            ImmutableMap.<Class<? extends ClickAction<?>>, ClickEvent.Action>of(
                    ClickAction.OpenUrl.class, ClickEvent.Action.OPEN_URL,
                    ClickAction.RunCommand.class, ClickEvent.Action.RUN_COMMAND,
                    ClickAction.SuggestCommand.class, ClickEvent.Action.SUGGEST_COMMAND,
                    ClickAction.ChangePage.class, ClickEvent.Action.CHANGE_PAGE
            );

    @SuppressWarnings("unchecked")
    public static ClickEvent getHandle(ClickAction<?> action) {
        Class<? extends ClickAction<?>> actionClass = (Class<? extends ClickAction<?>>) action.getClass();
        ClickEvent.Action type = TYPES.get(actionClass);
        if (type == null) {
            throw new UnsupportedOperationException(actionClass.toString());
        }

        return new ClickEvent(type, action.getResult().toString());
    }

}
