(ns om-next-test.style.dashboard
  (:require [garden.units :refer [px em percent]]
            [om-next-test.style.colour :as colour]
            [om-next-test.style.values :as values]))

(def style [[:.dashboard
             {:height (percent 100)
              :min-width (px 800)}
             [:.heading
              {:background-color colour/dash-heading-bg
               :padding-left (px 15)
               :box-shadow "0px 2px 4px #888"
               :position :relative
               :z-index 50}
              [:h1
               {:padding (px 0)
                :margin (px 0)
                :display :inline-block
                :width (em 8)}]
              [:.shared-search-input
               {:display :inline-flex
                :font-size (px 14)
                :vertical-align :super
                :margin-left (em 1)}
               [:form
                {:width (em 36)}]]]
             [:.content
              {:position :absolute
               :top values/app-peripheral-height
               :bottom 0
               :left 0
               :right 0
               :overflow-y :auto
               :overflow-x :hidden}
              [:#container
               {:width (percent 99)}]]]])
