package ictgradschool.project.util;

public class HashInfo {
    public final byte[] salt;
    public final String saltBase64;
    public final byte[] hash;
    public final String hashBase64;

    public HashInfo(byte[] salt, String saltBase64, byte[] hash, String hashBase64) {
        this.salt = salt;
        this.saltBase64 = saltBase64;
        this.hash = hash;
        this.hashBase64 = hashBase64;
    }

    @Override
    public String toString() {
        return "HashInfo{" +
                "saltBase64='" + saltBase64 + '\'' +
                ", hashBase64='" + hashBase64 + '\'' +
                '}';
    }
}
