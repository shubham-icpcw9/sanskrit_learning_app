package com.example.android.sanskrit;

public class Word {

    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * String resource ID for the default translation of the word
     */
    private String mDefaultTranslation;
    /**
     * String resource ID for the Miwok translation of the word
     */
    private String mSansTranslation;
    /**
     * Audio resource ID for the word
     */
    private int mAudioResourceId;
    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public Word(String defaultTranslation, String sansTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mSansTranslation = sansTranslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String sansTranslation, int imageResourceId,
                int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mSansTranslation = sansTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the string resource ID for the default translation of the word.
     */
    public String getDefaultTranslationId() {
        return mDefaultTranslation;
    }

    /**
     * Get the string resource ID for the Miwok translation of the word.
     */
    public String getMiwokTranslationId() {
        return mSansTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
