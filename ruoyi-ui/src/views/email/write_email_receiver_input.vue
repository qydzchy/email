<!-- EmailInput.vue -->
<template>
  <div class="value">
    <div class="component-email-input clearfix receiver">
      <div class="email-wrap show-last-input">
        <div
          v-for="(email, index) in emailList"
          :key="index"
          class="email-item email-item__draggable"
          data-draggable="true"
        >
          <span class="mm-tooltip email-info">
            <span class="mm-tooltip-trigger">
              <div>
                <span class="mm-tooltip all-type-avatar-wrapper client-colleague small">
                  <span class="mm-tooltip-trigger">
                    <i class="m-icon avatar-icon icon-new-customer-avatar"></i>
                  </span>
                </span>
                <span class="name">{{ email }}</span>
                <span
                  class="m-icon icon-cross-thin"
                  @click="removeEmail(index)"
                ></span>
              </div>
            </span>
          </span>
        </div>
        <div class="email-input last">
          <input
            type="text"
            v-model="currentEmail"
            :placeholder="placeholder"
            @keyup.enter="addEmail"
          >
        </div>
      </div>
      <div class="email-item total-email-info" :style="{ display: emailList.length > 0 ? '' : 'none' }">
        <span class="email-info">共 {{ emailList.length }} 个</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      required: true
    },
    placeholder: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      label: '',
      emailList: [],
      currentEmail: ''
    };
  },
  methods: {
    addEmail() {
      if (this.currentEmail) {
        this.emailList.push(this.currentEmail);
        this.currentEmail = '';
        this.$emit('update', {
          label: this.label,
          emails: this.emailList
        });
      }
    },
    removeEmail(index) {
      this.emailList.splice(index, 1);
      this.$emit('update', {
        label: this.label,
        emails: this.emailList
      });
    }
  }
};
</script>
