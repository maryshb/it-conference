package sii.itconference.services.impl;

import org.springframework.stereotype.Service;
import sii.itconference.model.Reservation;
import sii.itconference.model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailSenderServiceImpl {

    Writer writer = null;

    public void sendMail(User user, Reservation reservation) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("notification_" + reservation.getReservationId() + ".txt"), StandardCharsets.UTF_8))) {
            writer.write(generateMail(user, reservation));
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
        } finally {
            try {
                writer.close();
            } catch (Exception ignored) {}
        }
    }

    private String generateMail(User user, Reservation reservation) {

        return getFormattedDate() + "\nZapisałeś się na zajęcia: "
                + getLectureName(reservation) + "\nDo zobaczenia " + getReceiver(user);
    }

    private String getFormattedDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return formatter.format(date);
    }

    private String getReceiver(User user) {
        return user.getUsername();
    }

    private String getLectureName(Reservation reservation) {
        return reservation.getLecture().getName();
    }
}