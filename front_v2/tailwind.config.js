/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{html,js}',
    './components/**/*.{html,js}'
  ],
  theme: {
    colors: {
      primary: {
        lightest: '#d4e9ff',
        lighter: '#a4d0fc',
        light: '#4da6ff',
        DEFAULT: '#0B84FF',
        dark: '#0066cc',
        darker: '#014282',
      },
      secondary: {
        lighter: '#fae0ca',
        light: '#f39e58',
        DEFAULT: '#ed7410',
        dark: '#bf5d0d',
        darker: '#632d01',
      },
      gray: {
        750: '#2B3544',
      },
      transparent: "transparent",
      'orange': '#FBCA48',
      'white': '#FFFFFF',
    },
    extend: {
      backgroundColor: {
        'orange-500': '#FFA500',
        'red-400': '#FF6347',
        'red-500': '#FF0000',
        'bg-orange-400': '#FBCA48',
        'bg-gray-200': '#F3F4F6',

        'bg-secondary-lighter': '#F5F5F5',
        'bg-secondary-light': '#E5E5E5',
        'bg-secondary': '#D4D4D4',
        'bg-secondary-dark': '#A6A6A6',
        'bg-secondary-darker': '#787878',
        'bg-secondary-darkest': '#4A4A4A',


      },

    },
  },
  plugins: [
    require('tailwindcss-gradients'),
  ],

  purge: ['./src/**/*.js', './src/**/*.jsx', './public/index.html'],


}


