/*
 * Copyright 2018 Xovis AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xovis.proguard_unscrambler;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.unscramble.UnscrambleSupport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proguard.retrace.ReTrace;

import javax.swing.*;
import java.io.*;

import static proguard.retrace.ReTrace.STACK_TRACE_EXPRESSION;

public class AnalyzeAction implements UnscrambleSupport, ApplicationComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(AnalyzeAction.class);

    @Nullable
    @Override
    public String unscramble(@NotNull Project project, @NotNull String text, @NotNull String logName, @Nullable JComponent settings) {

        try (
             //create stringWriter to write out the stacktrace
             StringWriter stringWriter = new StringWriter();
             PrintWriter writer = new PrintWriter(stringWriter);

             //create reader to read stacktrace
             LineNumberReader reader = new LineNumberReader(new BufferedReader(new StringReader(text)))) {

            //create Mappingfile
            File mappingfile = new File(logName);

            //deobfuscate the stacktrace
            new ReTrace(STACK_TRACE_EXPRESSION, false, mappingfile).retrace(reader, writer);
            return stringWriter.toString();
        } catch (IOException ex) {
            LOGGER.error("Couldn't analyse stacktrace", ex);
            return null;
        }
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Proguard";
    }

    @Nullable
    @Override
    public JComponent createSettingsComponent() {
        return null;
    }
}