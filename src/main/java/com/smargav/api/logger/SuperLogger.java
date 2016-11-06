package com.smargav.api.logger;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class SuperLogger {
    private static List<String> attachments;
    private static String email;


    /*
     * private static void createNotfication(Context context) { String appName =
     * "Test"; String title = appName + " crashed"; String content =
     * "Send an email to developers";
     * NotificationUtils.createNotification(context, title,
     * content,getEmailIntent(), 1001,false); }
     */

    public static Intent getEmailIntent(String report, Context ctx) {
        String appName = ctx.getPackageName();
        String subject = appName + " Crashed";

        String emailContent = appName + " \nReport:\n" + report + "\n \n-AUTOGENERATED E-MAIL";
        Intent emailIntent = Email.getEmailIntent(new String[]{"amit@smargav.com"}, subject,
                emailContent, getAttachments());
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return emailIntent;
    }

    public static List<String> getAttachments() {
        return attachments;
    }

    public static void setAttachments(List<String> attachments) {
        if (SuperLogger.attachments == null) {
            SuperLogger.attachments = new ArrayList<>();
        }
        SuperLogger.attachments = attachments;
    }

    /*
         * private static void createNotfication(Context context) { String appName =
         * "Test"; String title = appName + " crashed"; String content =
         * "Send an email to developers";
         * NotificationUtils.createNotification(context, title,
         * content,getEmailIntent(), 1001,false); }
         */
    public static Intent getEmailIntent(String subject, String emailContent, Context ctx) {
        List<String> attachment = new ArrayList<String>();

        Intent emailIntent = Email.getEmailIntent(new String[]{"amit@smargav.com"}, subject,
                emailContent, attachment);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return emailIntent;
    }


    public static class Email {
        public static void startEmailActivity(Context context, String[] emailTo, String[] emailCC,
                                              String subject, String emailText, List<String> filePaths) {
            // need to "send multiple" to get more than one attachment
            final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            emailIntent.setType("text/plain");
            if (emailTo != null) {
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailTo);
            }
            if (emailCC != null) {
                emailIntent.putExtra(Intent.EXTRA_CC, emailCC);
            }
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
            // has to be an ArrayList
            if (filePaths != null) {
                ArrayList<Uri> uris = new ArrayList<Uri>();
                // convert from paths to Android friendly Parcelable Uri's
                for (String file : filePaths) {
                    File fileIn = new File(file);
                    Uri u = Uri.fromFile(fileIn);
                    uris.add(u);
                }
                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

            }
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }

        public static Intent getEmailIntent(String[] emailTo, String[] emailCC, String subject,
                                            String emailText, List<String> attachments) {
            // need to "send multiple" to get more than one attachment
            final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            emailIntent.setType("text/plain");
            if (emailTo != null) {
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailTo);
            }
            if (emailCC != null) {
                emailIntent.putExtra(Intent.EXTRA_CC, emailCC);
            }
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
            // has to be an ArrayList
            if (attachments != null) {
                ArrayList<Uri> uris = new ArrayList<Uri>();
                // convert from paths to Android friendly Parcelable Uri's
                for (String file : attachments) {
                    File fileIn = new File(file);
                    Uri u = Uri.fromFile(fileIn);
                    uris.add(u);
                }
                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

            }
            return (Intent.createChooser(emailIntent, "Send mail..."));
        }

        public static Intent getEmailIntent(String[] emailTo, String subject, String emailText,
                                            List<String> attachments) {
            // need to "send multiple" to get more than one attachment
            final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            emailIntent.setType("text/plain");
            if (emailTo != null) {
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailTo);
            }
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
            // has to be an ArrayList
            if (attachments != null) {
                ArrayList<Uri> uris = new ArrayList<Uri>();
                // convert from paths to Android friendly Parcelable Uri's
                for (String file : attachments) {
                    File fileIn = new File(file);
                    Uri u = Uri.fromFile(fileIn);
                    uris.add(u);
                }
                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

            }
            return (Intent.createChooser(emailIntent, "Send mail..."));
        }

    }

}
