package io.github.djhaskin987.methuselah.model;

import java.io.InputStream;

public interface ContentStore {

    /**
     * Store content in the file content store under a particular key.
     *
     * @param size
     *                    the size of the content in bytes.
     * @param content
     *                    the InputStream from which the content of the file
     *                    will be read.
     * @return the content addressable key by which to address this content in
     *         the future.
     */
    String storeContent(Long size, InputStream content);

    /**
     * Get content from the file content store under a particular key.
     *
     * @param key
     *                the key to the file content object in storage, previously
     *                returned by this object (or one like it).
     * @return an InputStream capable of reading the content.
     */
    InputStream getContent(String key);
}
