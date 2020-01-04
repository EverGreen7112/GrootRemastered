package com.evergreen.everlib.shuffleboard.handlers;

/**
 * Explorer
 */
public class Explorer {

    private String m_workingDirectory;

    public Explorer() {
        m_workingDirectory = "";
    }

    public Explorer(String path) {
        m_workingDirectory = path;
    }

    public Explorer cd(String path) {
        if (path.charAt(0) == '/') {
            m_workingDirectory = "";
            path = path.substring(0, path.length());
        }

        for (String folder : path.split("/")) {
            if (folder == "..") {
                m_workingDirectory = m_workingDirectory.substring(
                    0, m_workingDirectory.lastIndexOf('/'));
            }

            else if (folder != ".") {
                m_workingDirectory += "/";
                m_workingDirectory += folder;
            }
        }

        return new Explorer(m_workingDirectory);


    }

    public String pwd() {
        return m_workingDirectory;
    }
}