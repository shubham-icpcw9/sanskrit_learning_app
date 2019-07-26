package com.example.android.sanskrit;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.sanskrit.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {


    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }

                }
            };


    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wordlist, container, false);
        //View rootView = getActivity().findViewById(R.id.list);
        final ArrayList<Word> words = new ArrayList<Word>();

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        words.add(new Word("one", "एकम्",
                R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "द्वे",
                R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "त्रयः",
                R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "चत्वारि",
                R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "पञ्च",
                R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "षट्",
                R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "सप्त",
                R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "अष्ट",
                R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "नव",
                R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "दश",
                R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);


        ListView listView = rootView.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word currWord = words.get(position);
                //Log.v("NumbersActivity", "Current word: " + currWord);
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), currWord.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        listView.setAdapter(itemsAdapter);

        return rootView;

    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}
