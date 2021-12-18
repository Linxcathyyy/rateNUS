<template>
  <div :key="comment.id" class="comment">
    <v-row>
      <v-col>
        <div class="comment-info">
          <Rating :score="comment.rating" />
          <p>Posted on: {{ date }} {{ time }}</p>
        </div>
      </v-col>
      <v-col
        align-self="center"
        align="right"
        class="mx-4"
        v-show="this.isCurrentUser"
      >
        <v-btn icon color="orange accent-4">
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
      </v-col>
    </v-row>
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
      isCurrentUser: false, // will change based on user log in
    };
  },
  mounted() {
    this.date = new Date(this.comment.timestamp).toLocaleDateString();
    this.time = new Date(this.comment.timestamp).toLocaleTimeString();
    // console.log("mounted: ", this.comment);
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
