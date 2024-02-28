<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item href="#">Libros</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <h3 class="text-center mt-3">Libros</h3>

    <b-container fluid>
      <b-row class="d-flex justify-content-center">
        <b-col cols="12" sm="12" md="8">
          <div>
            <b-carousel
              id="carousel-1"
              v-model="slide"
              :interval="4000"
              controls
              indicators
              background="#ababab"
              img-width="1024"
              img-height="480"
              style="text-shadow: 1px 1px 2px #333"
              @sliding-start="onSlideStart"
              @sliding-end="onSlideEnd"
            >
              <!-- Text slides with image -->
              <b-carousel-slide
                caption="First slide"
                text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                img-src="https://picsum.photos/1024/480/?image=52"
              ></b-carousel-slide>

              <!-- Slides with custom text -->
              <b-carousel-slide
                img-src="https://picsum.photos/1024/480/?image=54"
              >
                <h1>Hello world!</h1>
              </b-carousel-slide>

              <!-- Slides with image only -->
              <b-carousel-slide
                img-src="https://picsum.photos/1024/480/?image=58"
              ></b-carousel-slide>

              <!-- Slides with img slot -->
              <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
              <b-carousel-slide>
                <template #img>
                  <img
                    class="d-block img-fluid w-100"
                    width="1024"
                    height="480"
                    src="https://picsum.photos/1024/480/?image=55"
                    alt="image slot"
                  />
                </template>
              </b-carousel-slide>

              <!-- Slide with blank fluid image to maintain slide aspect ratio -->
              <b-carousel-slide
                caption="Blank Image"
                img-blank
                img-alt="Blank image"
              >
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Suspendisse eros felis, tincidunt a tincidunt eget, convallis
                  vel est. Ut pellentesque ut lacus vel interdum.
                </p>
              </b-carousel-slide>
            </b-carousel>
          </div>
        </b-col>
      </b-row>
      <b-row class="mt-5">
        <b-col cols="12" sm="12" md="10">
          <b-row class="mt-3 text-center">
            <b-col cols="12" sm="12" md="4">
              <b-button>Ordenar por autor</b-button>
            </b-col>
            <b-col cols="12" sm="12" md="4">
              <b-button>Ordenar por fecha</b-button>
            </b-col>
            <b-col cols="12" sm="12" md="4">
              <b-button>Mostrar si tiene imagen</b-button>
            </b-col>
          </b-row>
          <b-row
            cols="12"
            class="align-items-stretch d-flex justify-content-center mt-5 ms-5"
            v-if="movies.length != 0"
          >
            <b-col v-for="movie in movies" :key="movie.id" class="my-2" cols="12" sm="12" md="4">
              <b-card
                title="Card Title"
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem"
                class="mb-2"
              >
                <b-card-text>
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </b-card-text>

                <b-button href="#" variant="primary">Go somewhere</b-button>
              </b-card>
            </b-col>
          </b-row>
        </b-col>
        <b-col cols="12" sm="12" md="2">
          <b-row class="text-center">
            <b-col cols="12" sm="12" class="mb-2">
              <b-button
                ><b-icon icon="plus" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
            <b-col cols="12" sm="12" class="mb-2">
              <b-button
                ><b-icon icon="pencil" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
            <b-col cols="12" sm="12" class="mb-2">
              <b-button
                ><b-icon icon="trash-fill" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      slide: 0,
      sliding: null,
      movies: [
        { name: "hola" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
        { name: "adios" },
      ],
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
  },
});
</script>
