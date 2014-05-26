package com.fsck.k9.mail.store.local;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.fsck.k9.mail.MessagingException;

public class TempFileBody extends BinaryAttachmentBody {
    private final File mFile;

    public TempFileBody(String filename) {
        mFile = new File(filename);
    }

    @Override
    public InputStream getInputStream() throws MessagingException {
        try {
            return new FileInputStream(mFile);
        } catch (FileNotFoundException e) {
            return new ByteArrayInputStream(LocalStore.EMPTY_BYTE_ARRAY);
        }
    }
}