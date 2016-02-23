(ns om-next-test.style.side
  (:require [garden.units :refer [px em]]
            [om-next-test.style.colour :as colour]
            [om-next-test.style.values :as values]))

(def style [[:#side-container
             {:position :relative
              :text-align :center
              :color colour/side-text
              :width values/app-peripheral-width}
             [:.side-element
              {:margin (em 1)}
              [:.side-link
               {:cursor :pointer
                :color colour/side-icons-inactive}
               [:&:hover
                {:color colour/side-text}]]]
             [:#side-upper
              {:width values/app-peripheral-width}]
             [:#side-lower
              {:position :fixed
               :bottom 0
               :width values/app-peripheral-width}]]])
