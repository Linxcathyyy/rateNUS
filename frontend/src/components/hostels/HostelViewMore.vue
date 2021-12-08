<template>
  <div v-if="hostel != null">
    <h1>Hostel View More with Id {{ $route.params.hostelId }}</h1>
    <Hostel :hostel="this.hostel" />
    <Comments />
    <CommentForm />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import Hostel from "./Hostel.vue";
import Comments from "../Comments.vue";
import CommentForm from "../CommentForm.vue";

export default {
  name: "HostelViewMore",
  components: { Hostel, Comments, CommentForm },
  data() {
    return {
      hostel: null,
      commentList: [],
    };
  },
  methods: {
    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          this.hostel = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getIndividualHostel();
  },
};
</script>

<style></style>
