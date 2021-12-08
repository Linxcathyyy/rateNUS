<template>
  <div class="comment-list">
    <div v-for="comment in commentList" :key="comment.id">
      <Comment :comment="comment" />
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Comment from "./Comment.vue";

export default {
  data() {
    return {
      commentList: [],
    };
  },
  components: {
    Comment,
  },
  methods: {
    getCommentList() {
      HostelRequest.getCommentList(this.$route.params.hostelId)
        .then((response) => {
          this.commentList = response.data;
          console.log(this.commentList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getCommentList();
  },
};
</script>

<style scoped>
.comment-list {
  padding: 20px 30px;
  margin: 20px 0px;
  background: rgba(214, 238, 245, 0.5);
  border-radius: 30px;
}
</style>
