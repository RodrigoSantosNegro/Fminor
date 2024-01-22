package com.example.aptmc;

import java.util.*;

public class Beethoven {
    private String[] allNotes = {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};

    private int[] createScalePattern(String modeName) {

        return switch (modeName.toLowerCase()) {
            case "ionian" -> new int[]{0, 2, 4, 5, 7, 9, 11}; // Ionian mode (Major scale)
            case "dorian" -> new int[]{0, 2, 3, 5, 7, 9, 10}; // Dorian mode
            case "phrygian" -> new int[]{0, 1, 3, 5, 7, 8, 10}; // Phrygian mode
            case "lydian" -> new int[]{0, 2, 4, 6, 7, 9, 11}; // Lydian mode
            case "mixolydian" -> new int[]{0, 2, 4, 5, 7, 9, 10}; // Mixolydian mode
            case "aeolian" -> new int[]{0, 2, 3, 5, 7, 8, 10}; // Aeolian mode (Natural Minor scale)
            case "locrian" -> new int[]{0, 1, 3, 5, 6, 8, 10}; // Locrian mode
            case "harmonicminor" -> new int[]{0, 2, 3, 5, 7, 8, 11}; // Harmonic Minor scale
            case "melodicminor" -> new int[]{0, 2, 3, 5, 7, 9, 11}; // Melodic Minor scale (Ascending)
            default -> new int[]{}; // Empty or default scale pattern

        };
    }

    public String[] createScale(String root, String scaleType) {
        int rootIndex = findNoteIndex(root);
        if (rootIndex == -1) {
            return new String[]{}; // Return empty array if root note is not found
        }

        int[] scalePattern = createScalePattern(scaleType);
        String[] scaleNotes = new String[scalePattern.length];

        for (int i = 0; i < scalePattern.length; i++) {
            scaleNotes[i] = allNotes[(rootIndex + scalePattern[i]) % allNotes.length];
        }

        return enharmonicAdaptation(scaleNotes);
    }

    private int findNoteIndex(String note) {
        for (int i = 0; i < 12; i++) { // Only loop through the first 12 notes

            if (allNotes[i].contains("/") && (note.contains("#") || note.contains("b"))) {
                String sharp = allNotes[i].split("/")[0]; // Get sharp note
                String flat = allNotes[i].split("/")[1]; // Get flat note
                if (Objects.equals(sharp, note) || Objects.equals(flat, note)) {
                    return i;
                }

            }

            if (Objects.equals(allNotes[i], note)) {
                return i;
            }
        }
        return -1; // Note not found
    }

    public String getIntervalName(String note1, String note2) {
        int index1 = findNoteIndex(note1);
        int index2 = findNoteIndex(note2);

        if (index1 == -1 || index2 == -1) {
            return "Invalid notes"; // One of the notes is not valid
        }

        int interval = index2 - index1;
        if (interval < 0) {
            interval += 12; // Correct handling for a 12-note octave
        }

        return switch (interval) {
            case 0 -> "Unison";
            case 1 -> "Minor Second";
            case 2 -> "Major Second";
            case 3 -> "Minor Third";
            case 4 -> "Major Third";
            case 5 -> "Perfect Fourth";
            case 6 -> "Tritone";
            case 7 -> "Perfect Fifth";
            case 8 -> "Minor Sixth";
            case 9 -> "Major Sixth";
            case 10 -> "Minor Seventh";
            case 11 -> "Major Seventh";
            default -> "Octave";
        };
    }

    public int calculateInterval(String note1, String note2) {
        int index1 = findNoteIndex(note1);
        int index2 = findNoteIndex(note2);

        if (index1 == -1 || index2 == -1) {
            return -1; // One of the notes is not valid
        }

        int interval = index2 - index1;
        if (interval < 0) {
            interval += 12; // Correct handling for a 12-note octave
        }

        return interval;
    }

    private String[] enharmonicAdaptation(String[] displaynotes) {
        for (int i = 0; i < displaynotes.length; i++) {
            if (displaynotes[i].contains("/")) {
                String sharp = displaynotes[i].split("/")[0]; // Get sharp note
                String flat = displaynotes[i].split("/")[1]; // Get flat note

                boolean isSharpUsedElsewhere = false;
                for (int j = 0; j < displaynotes.length; j++) {
                    if (i != j && (displaynotes[j].startsWith(sharp.substring(0, 1)) || displaynotes[j].endsWith(sharp.substring(0, 1)))) {
                        isSharpUsedElsewhere = true;
                        break;
                    }
                }

                if (isSharpUsedElsewhere) {
                    displaynotes[i] = flat; // Use flat if sharp is used elsewhere
                } else {
                    displaynotes[i] = sharp; // Use sharp otherwise
                }
            }
        }
        return displaynotes;
    }

    public String[] getScaleChords(String root, String scaleType) {
        // Create the scale using the given root and scale type
        String[] scale = createScale(root, scaleType);
        // Initialize an array to hold only the notes of the chords
        String[] scaleChords = new String[scale.length];

        // Loop through each note in the scale to create chords
        for (int i = 0; i < scale.length; i++) {
            // Get the notes of the chord (root, third, fifth)
            String[] chordNotes = getChordNotes(scale, i);
            // Store only the notes of the chord in the array, joined by commas
            scaleChords[i] = String.join(", ", chordNotes);
        }

        // Return the array of chord notes
        return scaleChords;
    }

    // Method to get the notes of a chord (triad) based on the scale
    private String[] getChordNotes(String[] scale, int rootIndex) {
        // Calculate the index of the third and fifth notes based on the root
        int thirdIndex = (rootIndex + 2) % scale.length;
        int fifthIndex = (rootIndex + 4) % scale.length;
        // Return the notes of the chord (root, third, fifth)
        return new String[]{scale[rootIndex], scale[thirdIndex], scale[fifthIndex]};
    }


    String getChordName(String root, String third, String fifth) {
        int rootToThird = calculateInterval(root, third);
        int rootToFifth = calculateInterval(root, fifth);

        String type = "";

        if (rootToThird == 4 && rootToFifth == 7) {
            type = " major";
        } else if (rootToThird == 3 && rootToFifth == 7) {
            type = " minor";
        } else if (rootToThird == 4 && rootToFifth == 8) {
            type = " augmented";
        } else if (rootToThird == 3 && rootToFifth == 6) {
            type = " diminished";
        } else if (rootToThird == 3 && rootToFifth == 8) {
            type = " minor augmented";
        } else if (rootToThird == 4 && rootToFifth == 6) {
            type = " majorb5";
        } else if (rootToThird == 2 && rootToFifth == 7) {  // sus2 chord
            type = " sus2";
        } else if (rootToThird == 5 && rootToFifth == 7) {  // sus4 chord
            type = " sus4";
        } else {
            type = " unknown"; // For combinations that don't match standard triads
        }

        return root + type;
    }


    public String[] giveMeRandomScale() {
        Random random = new Random();
        String[] modes = {
                "Ionian", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian",
                "Harmonic Minor", "Melodic Minor"
        };

        String[] scale;
        do {
            String root = allNotes[random.nextInt(12)];
            String selectedMode = modes[random.nextInt(modes.length)];
            scale = createScale(root, selectedMode.toLowerCase());
        } while (scale.length == 0);

        return scale;
    }


    public String[] giveMeRandomChord() {
        Random random = new Random();
        String[] modes = {
                "Ionian", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian",
                "Harmonic Minor", "Melodic Minor"
        };

        String[] chords;
        do {
            String root = allNotes[random.nextInt(12)];
            String selectedMode = modes[random.nextInt(modes.length)];
            chords = getScaleChords(root, selectedMode);
        } while (chords.length == 0 || Arrays.stream(chords).anyMatch(String::isEmpty));

        String randomChord = chords[random.nextInt(chords.length)];
        String[] chordNotes = randomChord.split(", ");
        String chordName = getChordName(chordNotes[0], chordNotes[1], chordNotes[2]);

        // Create a new array to include the chord name and the notes
        String[] chordDetails = new String[4];
        chordDetails[0] = chordName; // First element is the chord name
        System.arraycopy(chordNotes, 0, chordDetails, 1, chordNotes.length); // Next elements are the notes

        return chordDetails;
    }

    public String[] giveMeTwoNotesAndInterval() {
        Random random = new Random();

        // Select two random notes and get their enharmonic equivalents
        String note1 = getEnharmonicEquivalent(allNotes[random.nextInt(12)]);
        String note2 = getEnharmonicEquivalent(allNotes[random.nextInt(12)]);

        // Calculate the interval between the two notes
        int interval = calculateInterval(note1, note2);

        // Return the two notes and the interval as a string array
        return new String[]{note1, note2, String.valueOf(interval)};
    }


    public String giveMeNote() {
        Random random = new Random();
        String[] scale;
        do {
            scale = giveMeRandomScale();
        } while (scale.length == 0);

        int a = random.nextInt(scale.length);
        return scale[a];
    }

    private String getEnharmonicEquivalent(String note) {
        Random random = new Random();
        if (!note.contains("/")) {
            return note; // Return the note as is if it doesn't have an enharmonic equivalent
        }

        // Split the note into its sharp and flat parts
        String[] parts = note.split("/");
        // Randomly return either the sharp or flat version
        return parts[random.nextInt(2)];


    }

}