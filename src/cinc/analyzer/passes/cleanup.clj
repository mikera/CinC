(ns cinc.analyzer.passes.cleanup)

(defn cleanup [ast]
  (let [ast (-> ast
              (update-in [:env] dissoc :locals)
              (update-in [:env] dissoc :loop-locals)
              (update-in [:env] dissoc :loop-locals-casts))]
    (if (= :local (:op ast))
      (dissoc (assoc ast :children (vec (remove #{:init} (:children ast)))) :init)
      ast)))
