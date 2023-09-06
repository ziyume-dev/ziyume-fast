<script setup lang="ts">
const items = ref([
  { title: 'Click Me' },
  { title: 'Click Me' },
  { title: 'Click Me' },
  { title: 'Click Me 2' },
])

definePageMeta({
  layout: 'admin',
})
</script>

<template>
  <div flex flex-wrap space-x-2 space-y-2>
    <v-btn>
      Button
    </v-btn>

    <v-col cols="auto">
      <v-btn density="compact" icon="mdi-plus"></v-btn>
    </v-col>

    <v-col cols="auto">
      <v-btn density="comfortable" icon="$vuetify"></v-btn>
    </v-col>

    <v-col cols="auto">
      <v-btn density="default" icon="mdi-open-in-new"></v-btn>
    </v-col>

    <v-chip prepend-icon="$vuetify">
      Chip
    </v-chip>

    <v-expansion-panels max-w-80>
      <v-expansion-panel
        title="Title"
        text="Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi, ratione debitis quis est labore voluptatibus! Eaque cupiditate minima"
      >
      </v-expansion-panel>
    </v-expansion-panels>

    <v-menu
      open-on-hover
    >
      <template v-slot:activator="{ props }">
        <v-btn
          color="primary"
          v-bind="props"
        >
          Dropdown
        </v-btn>
      </template>

      <v-list>
        <v-list-item
          v-for="(item, index) in items"
          :key="index"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-tooltip text="Tooltip">
      <template v-slot:activator="{ props }">
        <v-btn v-bind="props">Tooltip</v-btn>
      </template>
    </v-tooltip>
  </div>
  <v-divider></v-divider>
  <div flex flex-wrap space-x-2 my-4>
    <v-checkbox label="Checkbox"></v-checkbox>

    <v-radio-group>
      <v-radio label="唱" value="1"></v-radio>
      <v-radio label="跳" value="2"></v-radio>
      <v-radio label="Rap" value="3"></v-radio>
    </v-radio-group>

    <v-select
      label="Select"
      :items="['唱', '跳', 'Rap', '篮球', 'Music']"
    ></v-select>

    <v-switch label="哎哟你干嘛~"></v-switch>

    <v-text-field label="厉不厉害你只因哥"></v-text-field>

    <v-btn-toggle
      color="primary"
    >
      <v-btn icon="mdi-format-align-left"></v-btn>
      <v-btn icon="mdi-format-align-center"></v-btn>
      <v-btn icon="mdi-format-align-right"></v-btn>
      <v-btn icon="mdi-format-align-justify"></v-btn>
    </v-btn-toggle>
  </div>
  <v-divider></v-divider>
  <div>
    <v-card
      max-width="400"
      mx-auto mt-2
    >
      <v-container>
        <v-row dense>
          <v-col cols="12">
            <v-card
              color="#385F73"
              theme="dark"
            >
              <v-card-title class="text-h5">
                Unlimited music now
              </v-card-title>

              <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle>

              <v-card-actions>
                <v-btn variant="text">
                  Listen Now
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>

          <v-col cols="12">
            <v-card
              color="#1F7087"
              theme="dark"
            >
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-card-title class="text-h5">
                    Supermodel
                  </v-card-title>

                  <v-card-subtitle>Foster the People</v-card-subtitle>

                  <v-card-actions>
                    <v-btn
                      ms-2
                      variant="outlined"
                      size="small"
                    >
                      START RADIO
                    </v-btn>
                  </v-card-actions>
                </div>

                <v-avatar
                  ma-3
                  size="125"
                  rounded="0"
                >
                  <v-img src="https://cdn.vuetifyjs.com/images/cards/foster.jpg"></v-img>
                </v-avatar>
              </div>
            </v-card>
          </v-col>

          <v-col cols="12">
            <v-card
              color="#952175"
              theme="dark"
            >
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-card-title class="text-h5">
                    Halcyon Days
                  </v-card-title>

                  <v-card-subtitle>Ellie Goulding</v-card-subtitle>

                  <v-card-actions>
                    <v-btn
                      ms-2
                      icon="mdi-play"
                      variant="text"
                    ></v-btn>
                  </v-card-actions>
                </div>

                <v-avatar
                  ma-3
                  size="125"
                  rounded="0"
                >
                  <v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
                </v-avatar>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<style scoped>

</style>
