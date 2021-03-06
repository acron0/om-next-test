(ns om-next-test.style.layout
  (:require [garden.units :refer [px percent]]
            [om-next-test.style.util :as util]
            [om-next-test.style.colour :as colour]
            [om-next-test.style.values :as values]))

(def style
  [[:body {:width (percent 100)
           :height (percent 100)}]

   [:#login {:position :absolute
             :top 0
             :left 0
             :bottom 0
             :right 0
             :background-color 'white
             :z-index 50}]

   [:#side {:background-color colour/side-bg
            :position :fixed
            :width values/app-peripheral-width
            :bottom 0
            :top 0
            :left 0
            :overflow :hidden
            :z-index 20
            :box-shadow "2px 0px 4px #888"}]

   [:#app {;:background (util/url "../img/bg.png")
           :position :absolute;
           :top 0
           :left values/app-peripheral-width
           :bottom 0
           :right 0
           :overflow :hidden}
    [:#split-container
     {:height (percent 100)}]]])
