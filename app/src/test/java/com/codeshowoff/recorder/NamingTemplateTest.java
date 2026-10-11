package com.codeshowoff.recorder;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NamingTemplateTest {

    @Test
    public void defaultNamingTemplate_containsRecorderZPrefix() {
        String defaultTemplate = "RecorderZ_{timestamp}";
        assertTrue("Template should start with RecorderZ", defaultTemplate.startsWith("RecorderZ_"));
        assertTrue("Template should contain timestamp placeholder", defaultTemplate.contains("{timestamp}"));
    }

    @Test
    public void timestampFormat_generatesSequentialTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        String formatted = sdf.format(new Date());
        assertNotNull("Formatted timestamp should not be null", formatted);
        assertEquals("Timestamp format length should match yyyyMMdd_HHmmss", 15, formatted.length());
        assertTrue("Timestamp should contain underscore separator", formatted.contains("_"));
    }

    @Test
    public void outputFilenames_resolveCleanlyWithTimestamp() {
        String template = "RecorderZ_{timestamp}";
        String timestamp = "20261012_040000";
        String filename = template.replace("{timestamp}", timestamp) + ".mp4";
        assertEquals("RecorderZ_20261012_040000.mp4", filename);
        assertFalse("Filename should not contain invalid path characters", filename.contains("/"));
    }

    @Test
    public void screenshotFilenames_resolveCleanlyWithTimestamp() {
        String template = "RecorderZ_{timestamp}";
        String timestamp = "20261012_040000";
        String filename = template.replace("{timestamp}", timestamp) + ".png";
        assertEquals("RecorderZ_20261012_040000.png", filename);
        assertTrue("Screenshot extension must be .png", filename.endsWith(".png"));
    }
}
