(ns om-next-test.ext-style.splitjs
  (:require [om-next-test.style.util :as util]
            [garden.units :refer [percent]]))

(def style [[:body
             {:box-sizing :border-box}]
            [:.split
             {:overflow-y :auto
              :overflow-x :hidden}
             ^:prefix {:box-sizing :border-box}]
            [:.gutter
             {:background-color "#eee"
              :background-repeat :no-repeat
              :background-position (percent 50)}
             [:&.gutter-vertical
              {:cursor :row-resize
               :background-image (util/url "../img/horizontal.png")}]]
            [:.split.split-horizontal :.gutter.gutter-horizontal]
            {:height (percent 100)
             :float :left}])
