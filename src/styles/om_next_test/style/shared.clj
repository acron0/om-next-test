(ns om-next-test.style.shared
  (:require [garden.units :refer [px em percent]]
            [om-next-test.style.colour :as colour]
            [om-next-test.style.values :as values]))

(def style [[:.shared-search-input
             {:position :relative}
             [:i
              {:position       :absolute
               :vertical-align :middle
               :margin         (em 0.24)}]
             [:#filter-input
              {:padding-left (px 30)
               :width        (percent 100)}]]])
