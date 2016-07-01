package ru.foreignlanguage.speak.video.parser;/*
 *  Copyright by Flow on 28.06.2016.
 
    Driver class for parsing
 */

import ru.foreignlanguage.common.Path;
import ru.foreignlanguage.common.Process;
import ru.foreignlanguage.speak.subs.Subtitle;
import ru.foreignlanguage.speak.subs.SubtitleFactory;
import ru.foreignlanguage.speak.subs.SubtitleFrase;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


public class FFMPEGDriver implements Driver {

    private Path filePath = new Path();

    private final String FFMPEG = Path.getResourcePath("libs/ffmpeg.exe", getClass().getClassLoader());

    private final String STREAMSTXT = "/streams.txt";

    private final String VIDEO_TO_STREAMS = "cmd /c " + Path.getResourcePath("libs/ffprobe.exe", getClass().getClassLoader())
                                                     + " -v error -show_entries stream=index,codec_name,codec_type ";
    private final String CONVERT_VIDEO= "cmd /c " + FFMPEG + " -i ";
    private final String CONCATENATE_AUDIO = "cmd /c " + FFMPEG + " -i -f concat -i  copy ";

    public void setProjectPath(final String filePath, boolean isCreated) throws FileAlreadyExistsException {
        try {
            this.filePath.get(filePath);
        } catch (FileAlreadyExistsException e) {
            if (isCreated) {
                System.out.println("isCreated = true, project path already exists.");
            } else {
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }

    /*  1. For given video file get streams
     *  2. For each stream generate separate file (audio and subtitles)
     */
    public void convertVideo(String filename) {
        try {
            if (filePath == null ) {
                throw new IOException("Set filePath first.");
            }
            String absFileName = Path.getAbsolutePath(filename);

            saveStreamsFromVideo(absFileName);

            saveSubtitlesFromVideo(Path.getAbsolutePath(absFileName));
            System.out.print("Parsing subtitles. ");
            ru.foreignlanguage.speak.subs.Parser subParser = SubtitleFactory
                                                    .getInstance().parse(filePath.toString(), "English");

            System.out.println("Done.");

            System.out.print("Searching for frase 'Riverrun'.");
            List<SubtitleFrase> frases = subParser.search("Riverrun");

            System.out.println(" Found " + frases.size() + " frases. ");

            System.out.println("Start saving audio.");
            String concat_str = FFMPEG;
            for (SubtitleFrase fr: frases) {
                System.out.print("Saving audio for next frase ... ");
                saveAudioForFrase(fr, Path.getAbsolutePath(absFileName));
                System.out.println("Done");
            }

            concatAudios(filePath.toString(), Path.getAbsolutePath("/english/Riverrun.mp3"));

            System.out.print("Job is done");
        } catch (IOException|InterruptedException e) {
            System.err.println(e); }
    }

    private void saveStreamsFromVideo(final String fileName) throws InterruptedException, IOException {
        try {
            String q = VIDEO_TO_STREAMS + fileName + " > " + filePath + "/streams.txt";
            Process.execute(q);
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private void saveSubtitlesFromVideo(final String filename) throws InterruptedException, IOException {
        try {
            Parser parser = new VideoParser();
            parser.loadVideoStreamInfo(filePath+STREAMSTXT);
            List<Stream> subs = parser.getSubtitleStreams();

            String q = CONVERT_VIDEO + filename + " ";
            for (Stream s: subs) {
                q += "-map 0:" + s.getIndex() + " -c copy "
                        + filePath + "/subs"+s.getIndex()+".srt ";
            }
            Process.execute(q);
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private void saveAudioForFrase(Subtitle sub, String filename) throws InterruptedException, IOException {
        String q;
        try {
            q = CONVERT_VIDEO + filename
                    + " -ss " + sub.getFromTime()
                    + " -to " + sub.getTillTime().addSecond()
                    + " -map 0:2 -c libmp3lame -b:a:0 128k "
                    + filePath + "/audio" + sub.hashCode() + ".mp3";

            Process.execute(q);
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private void concatAudios(final String filePath, final String translateFrase) throws IOException, InterruptedException {
        String concat_str = FFMPEG, q;
        String complex_filter = "";
        try {
            List<java.nio.file.Path> subFiles = Files.list(new File(filePath).toPath())
                    .filter((p) -> p.getFileName().toString().endsWith(".mp3"))
                    .collect(Collectors.toList());

            if (subFiles.size() >0) {
                int concatCount = 0;
                for (java.nio.file.Path f: subFiles){
                    concat_str += " -i \"" + translateFrase + "\"";
                    concat_str += " -i \"" + f.toString() + "\"";
                    complex_filter += "[" + concatCount++ + ":a:0] ";
                    complex_filter += "[" + concatCount++ + ":a:0] ";
                }

                concat_str += " -filter_complex \"" + complex_filter + " concat=n=" + (concatCount) + ":v=0:a=1 [a]\" -map \"[a]\""
                        + " -f wav -vn -y " + filePath + "/audios.wav";
            }

            System.out.println(concat_str);
            Process.execute(concat_str);
        } catch (IOException|InterruptedException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
}

