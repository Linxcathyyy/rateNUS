package com.rateNUS.backend.util;

public class CommentUtil {
    public static double addComment(double rating, int commentCount, double newRating) {
        return commentCount == 0
                ? newRating
                : (commentCount * rating + newRating) / (commentCount + 1);
    }

    public static double updateComment(double rating, int commentCount, double oldRating, double newRating) {
        return (commentCount * rating - oldRating + newRating) / commentCount;
    }

    public static double deleteComment(double rating, int commentCount, double ratingToRemove) {
        if (commentCount == 1) {
            return -1;
        } else {
            return (commentCount * rating - ratingToRemove) / (commentCount - 1);
        }
    }
}
