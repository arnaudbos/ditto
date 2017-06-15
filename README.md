# Ditto

<a title="Ditto Pokemon" href="https://bulbapedia.bulbagarden.net/wiki/Ditto_(Pok%C3%A9mon)"><img width="128" alt="Ditto Pokemon" src="https://archives.bulbagarden.net/media/upload/a/ad/132Ditto_AG_anime.png"/></a>

Ditto (for [Ditto machine](https://www.google.fr/search?q=Ditto+machine))
is a Leiningen template created for my own use, based on
[ring](https://github.com/ring-clojure/ring),
[compojure](https://github.com/weavejester/compojure) and
[component](https://github.com/stuartsierra/component).

### Content

The template will generate a ready to use component `system-map` based on
an HTTP Jetty server hosting a compojure api `handler` itself depending on
a `database` component.

The `database` component is an example of a component whose implementation
can be selected by changing the `config.edn` configuration file.
For instance to use an in-memory `dev-database` during development and a
"real" database component in production.

The generated `myproject.database` namespace can be taken as an example to
create more components for which you would want to use alternative
implementations depending on the current `profile`.

### Usage

Get it:

    git clone git@github.com:arnaudbos/ditto.git

Install:

    lein install

Use:

    lein new ditto my-project

### Also included

* `environ` for loading a different `config.edn` file depending on the profile
* `org.clojure/core.async` because I generally use it in my projects
* `com.taoensso/timbre` for easy logging

## License

Copyright © 2017 Arnaud Bos

Distributed under the Eclipse Public License, the same as Clojure.
