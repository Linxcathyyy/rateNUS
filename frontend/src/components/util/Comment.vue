<template>
  <div :key="comment.id" class="comment">
    <div class="comment-info">
        <Rating :score="comment.rating" />
        <p>
          Posted on: {{ date }} {{ time }}
        </p>
    </div>
    <p class="description">
      {{ comment.text }}
    </p> 
  </div>
</template>

<script>
import Rating from "../commons/Rating.vue";

export default {
  name: "Comment",
  components: {
    Rating,
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

.description {
  text-align: left;
}

.comment {
  border-bottom: 1px solid darkgray;
  margin-bottom: 1rem;
}

.comment-info {
  text-align: start;
  font-size: 0.83em;
}

</style>
