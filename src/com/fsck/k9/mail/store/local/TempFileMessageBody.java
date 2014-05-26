package com.fsck.k9.mail.store.local;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.james.mime4j.util.MimeUtil;

import com.fsck.k9.mail.CompositeBody;
import com.fsck.k9.mail.MessagingException;

public class TempFileMessageBody extends TempFileBody implements CompositeBody {

    public TempFileMessageBody(String filename) {
        super(filename);
    }

    @Override
    public void writeTo(OutputStream out) throws IOException, MessagingException {
        AttachmentMessageBodyUtil.writeTo(this, out);
    }

    @Override
    public void setUsing7bitTransport() throws MessagingException {
        // see LocalAttachmentMessageBody.setUsing7bitTransport()
    }

    @Override
    public void setEncoding(String encoding) throws MessagingException {
        if (!MimeUtil.ENC_7BIT.equalsIgnoreCase(encoding)
                && !MimeUtil.ENC_8BIT.equalsIgnoreCase(encoding)) {
            throw new MessagingException(
                    "Incompatible content-transfer-encoding applied to a CompositeBody");
        }
        mEncoding = encoding;
    }
}