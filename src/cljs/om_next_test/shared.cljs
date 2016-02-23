(ns om-next-test.shared
  (:require [om.next :as om :refer-macros [defui]]))

(defn search-filter
  [placeholder on-input]
  [:div.shared-search-input
   [:form.pure-form
    {:key "search-filter-form"}
    [:div
     {:key "search-filter-inner-div"}
     [:i.material-icons.md-dark
      {:key "icon"}
      "search"]
     [:input.pure-input-rounded
      {:key "filter-input"
       :id "filter-input"
       :type "text"
       :placeholder placeholder
       :on-input (fn [e]
                   (if (fn? on-input)
                     (on-input (.. e -target -value)))
                   (.preventDefault e))}]]]])
