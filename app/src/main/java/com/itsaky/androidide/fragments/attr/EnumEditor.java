/*
 *  This file is part of AndroidIDE.
 *
 *  AndroidIDE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AndroidIDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with AndroidIDE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.itsaky.androidide.fragments.attr;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;

import java.util.Objects;

/**
 * @author Akash Yadav
 */
public class EnumEditor extends FixedValueEditor {
    
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        
        Objects.requireNonNull (this.attribute);
        Objects.requireNonNull (this.chipGroup);
        
        this.chipGroup.setSingleSelection (true);
        
        final var attr = this.attribute.getAttr ();
        for (var value : attr.possibleValues) {
            if (TextUtils.isEmpty (value)) {
                continue;
            }
            
            this.chipGroup.addView (newChip (value, value.equals (this.attribute.getValue ())));
        }
        
        this.chipGroup.setOnCheckedChangeListener ((group, checkedId) -> {
            final var chip = (Chip) group.findViewById (checkedId);
            final var val = chip.getText ().toString ().trim ();
            notifyValueChanged (val);
        });
    }
}
