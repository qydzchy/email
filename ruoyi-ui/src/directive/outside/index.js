export default {
    bind(el, binding) {
        function clickHandler(e) {
            if (el.contains(e.target)) {
                return false;
            }
            if (binding.expression) {
                binding.value(e);
            }
        }
        el.__vueClickOutside__ = clickHandler;
        document.addEventListener('click', clickHandler);
    },
    update() { },
    unbind(el) {
        document.removeEventListener('click', el.__vueClickOutside__);
        delete el.__vueClickOutside__;
    },
}