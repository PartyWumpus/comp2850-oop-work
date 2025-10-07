{
  inputs = {
    flake-utils.url = "github:numtide/flake-utils";
    nixpkgs.url = "github:nixos/nixpkgs";
  };

  outputs = {
    self,
    nixpkgs,
    flake-utils,
  }:
    flake-utils.lib.eachDefaultSystem (system: let
      pkgs = import nixpkgs {
        inherit system;
      };
    in
      with pkgs; {
        formatter = pkgs.alejandra;
        devShell = mkShell {
          packages = [
            gradle
            kotlin-language-server
            jdk23
            kotlin
          ];
        };
      });
}
