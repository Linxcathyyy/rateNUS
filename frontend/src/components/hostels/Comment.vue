<template>
  <div>
    <div :key="comment.id" class="comment">
      <div class="comment-info">
        <Rating :score="comment.rating" />
      </div>
      <Description :description="comment.text" />
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
.comment {
  list-style-type: none;
  background: rgba(201, 218, 241, 0.5);
  margin: 20px 0px;
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.comment-info {
  flex-shrink: 0;
  width: 20%;
  padding: 10px 40px;
}
</style>
