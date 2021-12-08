<template>
  <div :key="comment.id" class="comment">
    <div class="comment-info">
      <Rating :score="comment.rating" />
      <p>
        Posted on:<br />
        {{ date }} {{ time }}
      </p>
    </div>
    <Description :description="comment.text" class="description" />
  </div>
</template>

<script>
import Rating from "./hostels/Rating.vue";
import Description from "./hostels/Description.vue";

export default {
  name: "Comment",
  components: {
    Rating,
    Description,
  },
  props: {
    comment: Object,
  },
  data() {
    return {
      date: null,
      time: null,
    };
  },
  mounted() {
    this.date = new Date(this.comment.timestamp).toLocaleDateString();
    this.time = new Date(this.comment.timestamp).toLocaleTimeString();
  },
};
</script>

<style scoped>
.comment {
  display: flex;
}

.comment-info {
  flex-shrink: 0;
  width: 18.5%;
  padding: 10px 40px;
}
</style>
