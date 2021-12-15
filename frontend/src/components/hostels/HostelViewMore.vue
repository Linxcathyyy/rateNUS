<template>
  <div v-if="hostel != null">
    <Hostel :hostel="this.hostel" />
    <ImageSlideShow :imageUrls="hostel.imageUrl" />
    <Facilities :facilities="this.hostel.facilities" />
    <CommentForm />
    <Comments />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import Hostel from "./Hostel.vue";
import Comments from "../Comments.vue";
import CommentForm from "../CommentForm.vue";
import Facilities from "./Facilities.vue";
import ImageSlideShow from "../ImageSlideShow.vue";

export default {
  name: "HostelViewMore",
  components: {
    Hostel,
    Comments,
    CommentForm,
    Facilities,
    ImageSlideShow,
  },
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
          console.log(response.data);
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
