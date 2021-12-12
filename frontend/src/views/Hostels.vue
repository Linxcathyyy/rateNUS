<template>
  <div>
    <h1>Hostel List</h1>
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                @input="updatePage"
                v-model="currentPage"
                class="my-4"
                :length="getTotalPages"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>


<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";
import SearchBar from "../components/util/SearchBar";

export default {
  name: "Hostels",
  components: {
    Hostel,
    SearchBar,
  },

  data() {
    return {
      hostelList: [],
      currentPage: 1,
      pageSize: 2,
      totalNumberOfHostels: 3,
    };
  },
  computed: {
    getTotalPages() {
      return Math.ceil(this.totalNumberOfHostels / this.pageSize);
    },
  },
  methods: {
    async getHostelList(pageNum, pageSize) {
      await HostelRequest.getHostelList(pageNum, pageSize)
        .then(async (response) => {
          this.hostelList = response.data;
          console.log(this.hostelList);
          if (this.hostelList.length == 0 && this.currentPage > 1) {
            await this.updatePage(this.currentPage - 1);
          }
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },

    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      await this.getHostelList(pageNumber - 1, this.pageSize);
    },

    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },

    handleSearch(keyword) {
      HostelRequest.findHostels(keyword)
        .then((response) => {
          console.log("search result" + response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

  },

  watch: {
      "currentPage": (newPage) => {
          this.updatePage(newPage);
      }
  },

  async mounted() { 
    await this.getHostelList(this.currentPage - 1, this.pageSize);
  },

  emits: ["handle-search"],
};
</script>

<style scope>
#hostel-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>
