<template>
  <div>
    <div :key="comment.id" class="comment">
      <div class="comment-info">
        <Rating :score="comment.rating" />
      </div>
      <Description :description="comment.text" />
      <p>Date Posted: {{ comment.timestamp}}</p>
    </div>
  </div>
</template>

<script>
import Rating from "./Rating.vue";
import Description from "./Description.vue";

export default {
  name: "Comment",
  components: {
    Rating,
    Description,
  },
  props: {
    comment: Object,
  },
};
</script>

<style>
</style>
