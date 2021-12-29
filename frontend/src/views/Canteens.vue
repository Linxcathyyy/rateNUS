<template>
<div>
  <div v-if="loading">
    <v-progress-circular
      indeterminate
      color="orange accent-4"
    ></v-progress-circular>
  </div>
  <div v-if="!loading" class="stalls">
    <SearchBar @handle-search="handleSearch" searchHint="Search for stalls" />
    <div v-if="!noResult">
      <div v-for="stall in stallList" :key="stall.id" class="stall-list">
        <div @click="goToViewMorePage(stall.id)" id="stall-click">
          <ItemCard type="stall" :item="stall" />
        </div>
      </div>
      <div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
                <v-pagination
                  @input="updatePage"
                  v-model="currentPage"
                  class="my-4"
                  :length="totalPages"
                  prev-icon="mdi-menu-left"
                  next-icon="mdi-menu-right"
                  color="orange accent-4"
                ></v-pagination>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
    <div v-if="noResult">
      <p>No results found for "{{this.currentKeyword}}"</p>
      <p>Try searching "Techno Egde" or "Frontier"</p>
    </div>
  </div>
</div>
</template>

<script>
import StallRequest from "../httpRequests/StallRequest";
import ItemCard from "../components/commons/ItemCard";
import SearchBar from "../components/util/SearchBar";

export default {
  name: "Stalls",
  components: {
    ItemCard,
    SearchBar,
  },

  data() {
    return {
      stallList: [],
      currentPage: 1,
      pageSize: 3,
      totalPages: 0,
      currentKeyword: "",
      hasBeenSearched: false,
      loading: false
    };
  },
  methods: {
    async getStallList(pageNum, pageSize) {
      this.loading = true;
      await StallRequest.getStallList(pageNum, pageSize)
        .then(async (response) => {
          console.log(response.data.content);
          this.stallList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error.response.data);
        });
      this.loading = false;
    },

    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      if (this.hasBeenSearched) {
        await this.handleSearch(this.currentKeyword);
      } else {
        await this.getStallList(pageNumber - 1, this.pageSize);
      }
    },

    goToViewMorePage(stallId) {
      this.$router.push("/stalls/" + stallId);
    },

    async handleSearch(keyword) {
      console.log("keyword: " + keyword);
      var page;
      if (this.currentKeyword != keyword) {
        // first search
        console.log("first search");
        page = 0;
      } else {
        page = this.currentPage - 1;
      }
      console.log("currentKeyword: " + this.currentKeyword);
      console.log("page: " + page);
      StallRequest.findStalls(keyword, page, this.pageSize)
        .then((response) => {
          console.log(response.data);
          this.currentPage = page + 1;
          this.stallList = response.data.content;
          this.totalPages = response.data.totalPages;
          this.currentKeyword = keyword;
          this.hasBeenSearched = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  async created() {
    await this.getStallList(this.currentPage - 1, this.pageSize);
  },

  computed: {
    noResult() {
      return this.stallList.length == 0 && this.hasBeenSearched;
    }
  },

  emits: ["handle-search"]
};
</script>

<style scope>
.stall-list {
  margin: auto;
}

#stall-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>

